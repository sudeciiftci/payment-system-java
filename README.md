# Payment System — Java 

A beginner-friendly Java project that simulates a payment screen with multiple payment methods. Built to practice **Object-Oriented Programming** concepts, especially interfaces and polymorphism.

---

## What I Learned

This project was my first real hands-on experience with **interfaces** in Java. The core idea was simple but powerful:

> Write one rule, let different classes follow it their own way.

All payment methods implement the same `PaymentMethod` interface, which means the system always calls `processPayment()` and `processRefund()` — but what happens inside depends on which class is running at that moment. This is **polymorphism** in action.

---

## OOP Concepts Used

- **Interface** — `PaymentMethod` defines the contract: every payment method must be able to process a payment and a refund.
- **Polymorphism** — the same method call behaves differently depending on the selected payment type at runtime.
- **Encapsulation** — each class manages its own data (card number, balance, QR code) privately with getters and setters.
- **Separation of concerns** — each class handles only its own logic. The UI does not care how payment works internally.

---

## Payment Methods

| Method | Logic |

| Credit Card | Checks available limit, deducts on success |
| Cash | Checks amount given, calculates change |
| QR Code | Validates QR code before approving |
| Gift Card | Checks balance, deducts on success, rejects if insufficient |

---

## Project Structure

```
payment-system-java/
└── src/
    ├── PaymentMethod.java   (interface)
    ├── CreditCard.java
    ├── Cash.java
    ├── QRCode.java
    ├── GiftCard.java
    └── PaymentApp.java      (JavaFX UI)
```

---

## How to Run

**Requirements:**
- Java 17+
- JavaFX SDK

## Notes

This is a student project built for learning purposes. The focus was understanding OOP design — not production-level code. No database, no real payment processing.
