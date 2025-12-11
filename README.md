# Java Swing Pizza Order Form: Complex Form Design

## 💡 Overview
This project involves creating a full-featured order entry form using the **Java Swing** framework. The primary goal is to gain practical experience with essential form components (`JCheckBox`, `JRadioButton`, `JComboBox`) and implement the business logic required to calculate and format a detailed receipt for a single pizza order. 

## 🎯 Implementation Goals
This lab demonstrates proficiency in:
1.  Designing a complex GUI layout using multiple nested `JPanel` containers.
2.  Implementing interactive input components (`JCheckBox`, `JRadioButton`, `JComboBox`).
3.  Managing component state and extracting user selections upon button click.
4.  Implementing detailed calculation logic (sub-total, tax, total).
5.  Advanced event handling, including using `JOptionPane` for confirmation dialogues.

## 🛠️ GUI Component and Layout Breakdown

The application is structured using several `JPanel` containers, each with a `TitledBorder` for clear organization:

| Panel Focus | Components Used | Notes |
| :--- | :--- | :--- |
| **Crust Type** | `JRadioButton` set | Options: {Thin, Regular, Deep-dish}. Only one can be selected. |
| **Size** | `JComboBox` | Options: {Small, Medium, Large, Super} with corresponding base costs. |
| **Toppings** | `JCheckBox` set | At least 6 toppings; each adds a fixed cost of $1.00. |
| **Order Display** | `JTextArea` inside a `JScrollPane` | Displays the final, formatted receipt output. |
| **Controls** | `JButton`s | Order, Clear, and Quit actions. |

## ⚙️ Core Logic and Event Handling

### 1. **Order Button**
* **Validation:** Ensures an order is compiled only if a **Crust** and **Size** are selected, and at least **one ingredient** is chosen.
* **Calculation:** Gathers all selected prices to calculate the sub-total, applies a **7% tax (.07)**, and calculates the final total.
* **Display:** Formats the complete order and cost into a register receipt format displayed in the `JTextArea`.

### 2. **Clear Button**
* Wipes the state of all input components (`JRadioButton`, `JCheckBox`, `JComboBox`) and clears the output `JTextArea`, preparing the form for a new order.

### 3. **Quit Button**
* Exits the application after presenting a confirmation dialogue using **`JOptionPane.ConfirmMessegeDialog`** to ensure the user truly wishes to quit.

### **Receipt Formatting Example**
The final output is precisely formatted to mimic a cash register receipt, including bolded labels and divider lines:
