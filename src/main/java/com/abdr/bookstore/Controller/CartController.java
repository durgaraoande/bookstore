package com.abdr.bookstore.Controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdr.bookstore.models.Book;
import com.abdr.bookstore.models.Cart;
import com.abdr.bookstore.models.CartItem;
import com.abdr.bookstore.models.Transaction;
import com.abdr.bookstore.models.User;
import com.abdr.bookstore.service.BookService;
import com.abdr.bookstore.service.CartItemService;
import com.abdr.bookstore.service.CartService;
import com.abdr.bookstore.service.TransactionService;
import com.abdr.bookstore.service.UserService;

import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/user/cart/add")
    public String addToCart(@RequestParam int bookId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            user.setCart(cart);
            userService.save(user);
            cartService.save(cart); // save the Cart before saving the CartItem
        }
        Book book = bookService.findById(bookId);
        Map<Integer, CartItem> cartItemsMap = cart.getItems().stream()
        .collect(Collectors.toMap(item -> item.getBook().getId(), item -> item));
        CartItem existingItem = cartItemsMap.get(bookId);
        if (existingItem == null) {
            CartItem item = new CartItem();
            item.setBook(book);
            item.setCart(cart);
            item.setQuantity(1); // set initial quantity to 1
            cartItemService.save(item);
            cart.getItems().add(item);
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemService.save(existingItem);
        }
        cartService.save(cart);
        return "redirect:/user/";
    }

    @GetMapping("/user/cart")
    public String showCart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();
        if (cart != null) {
            model.addAttribute("cart", cart);
        }
        if (cart != null && cart.getItems().isEmpty()) {
            model.addAttribute("cart", null);
        }
        return "cart";
    }

    @PostMapping("/user/cart/update")
    public String updateQuantity(@RequestParam int itemId, @RequestParam int quantity) {
        CartItem item = cartItemService.findById(itemId);
        item.setQuantity(quantity);
        cartItemService.save(item);
        return "redirect:/user/cart";
    }

    @PostMapping("/user/cart/remove")
    public String removeFromCart(@RequestParam int itemId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();
        CartItem item = cartItemService.findById(itemId);
        cart.getItems().remove(item);
        cartItemService.delete(item);
        return "redirect:/user/cart";
    }


    @PostMapping("/user/cart/checkout")
    public ResponseEntity<String> checkout(Principal principal) {
       try{
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();

        // Calculate the total price of the items in the cart
        double totalPrice = cart.getItems().stream()
                    .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                    .sum();
        // Clear the cart
        cartService.clearCart(cart);
    
        // Add the transaction to the user's transaction history
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(totalPrice);
        transactionService.save(transaction);
        return ResponseEntity.ok("/user/checkout-success?transactionId=" + transaction.getId());
       }
       catch (Exception e) {
        // Log the exception
        logger.error("Checkout failed", e);

        // Return an error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Checkout failed. Please try again.");
    }
    }

    @GetMapping("/user/checkout-success")
    public String checkoutSuccess(@RequestParam("transactionId") Long transactionId, Model model) {
        Optional<Transaction> transaction = transactionService.findById(transactionId);
        model.addAttribute("transaction", transaction);
    
        // Add your payment methods to the model
        List<String> paymentMethods = Arrays.asList("Credit Card", "Debit Card", "PayPal", "Bank Transfer");
        model.addAttribute("paymentMethods", paymentMethods);
    
        return "checkout-success";
    }
}
