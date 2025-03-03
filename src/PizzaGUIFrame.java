package src;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PizzaGUIFrame extends JFrame {

    private JPanel mainPnl;
    private JPanel selectionPnl;
    private JPanel crustPnl;
    private JPanel sizePnl;
    private JPanel toppingsPnl;
    private JPanel displayPnl;
    private JPanel controlPnl;

    private JRadioButton thinCrustRB;
    private JRadioButton regularCrustRB;
    private JRadioButton deepDishCrustRB;
    private ButtonGroup crustGroup;

    private JComboBox<String> sizeCB;
    private final double baseCostSmall = 8.00;
    private final double baseCostMedium = 12.00;
    private final double baseCostLarge = 16.00;
    private final double baseCostSuper = 20.00;
    private double currentBaseCost = 0.00;

    private JCheckBox pepperoniCB;
    private JCheckBox sausageCB;
    private JCheckBox baconCB;
    private JCheckBox greenPeppersCB;
    private JCheckBox bananaPeppersCB;
    private JCheckBox onionsCB;
    private final double toppingCost = 1.00;

    private JTextArea orderTA;
    private JScrollPane scroller;

    private JButton orderBtn;
    private JButton clearBtn;
    private JButton quitBtn;

    private final double TAX_RATE = 0.07;

    public PizzaGUIFrame() {
        setTitle("Pizza Order Form");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle quit explicitly

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        selectionPnl = new JPanel(new GridLayout(1, 3)); // Arrange crust, size, toppings horizontally

        createCrustPanel();
        selectionPnl.add(crustPnl);

        createSizePanel();
        selectionPnl.add(sizePnl);

        createToppingsPanel();
        selectionPnl.add(toppingsPnl);

        mainPnl.add(selectionPnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setVisible(true);

        // Handle window closing event for the quit button functionality
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                handleQuit();
            }
        });
    }

    private void createCrustPanel() {
        crustPnl = new JPanel();
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Crust Type"));
        crustPnl.setLayout(new GridLayout(3, 1));

        thinCrustRB = new JRadioButton("Thin");
        regularCrustRB = new JRadioButton("Regular", true); // Default selection
        deepDishCrustRB = new JRadioButton("Deep-dish");

        crustGroup = new ButtonGroup();
        crustGroup.add(thinCrustRB);
        crustGroup.add(regularCrustRB);
        crustGroup.add(deepDishCrustRB);

        crustPnl.add(thinCrustRB);
        crustPnl.add(regularCrustRB);
        crustPnl.add(deepDishCrustRB);
    }

    private void createSizePanel() {
        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
        sizeCB = new JComboBox<>(new String[]{"Small", "Medium", "Large", "Super"});
        sizeCB.addActionListener((ActionEvent e) -> updateBaseCost());
        sizePnl.add(sizeCB);
        updateBaseCost(); // Initialize base cost
    }

    private void updateBaseCost() {
        String size = (String) sizeCB.getSelectedItem();
        switch (size) {
            case "Small":
                currentBaseCost = baseCostSmall;
                break;
            case "Medium":
                currentBaseCost = baseCostMedium;
                break;
            case "Large":
                currentBaseCost = baseCostLarge;
                break;
            case "Super":
                currentBaseCost = baseCostSuper;
                break;
        }
    }

    private void createToppingsPanel() {
        toppingsPnl = new JPanel();
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));
        toppingsPnl.setLayout(new GridLayout(6, 1));

        pepperoniCB = new JCheckBox("Turkey Pepperoni");
        sausageCB = new JCheckBox("Turkey Sausage");
        baconCB = new JCheckBox("Turkey Bacon");
        greenPeppersCB = new JCheckBox("Green Peppers");
        bananaPeppersCB = new JCheckBox("Banana Peppers");
        onionsCB = new JCheckBox("Onions");

        toppingsPnl.add(pepperoniCB);
        toppingsPnl.add(sausageCB);
        toppingsPnl.add(baconCB);
        toppingsPnl.add(greenPeppersCB);
        toppingsPnl.add(bananaPeppersCB);
        toppingsPnl.add(onionsCB);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel();
        displayPnl.setBorder(new TitledBorder(new EtchedBorder(), "**Order Details**"));
        orderTA = new JTextArea(15, 35);
        orderTA.setEditable(false);
        scroller = new JScrollPane(orderTA);
        displayPnl.add(scroller);
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        orderBtn = new JButton("Order");
        orderBtn.addActionListener((ActionEvent e) -> displayOrder());

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent e) -> clearOrder());

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent e) -> handleQuit());

        controlPnl.add(orderBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);
    }

    private void handleQuit() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Confirm Quit",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void displayOrder() {
        String crustType = "";
        if (!thinCrustRB.isSelected() && !regularCrustRB.isSelected() && !deepDishCrustRB.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a crust.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (thinCrustRB.isSelected()) {
            crustType = "Thin";
        } else if (regularCrustRB.isSelected()) {
            crustType = "Regular";
        } else if (deepDishCrustRB.isSelected()) {
            crustType = "Deep-dish";
        }

        String size = (String) sizeCB.getSelectedItem();

        ArrayList<String> selectedToppings = new ArrayList<>();
        if (pepperoniCB.isSelected()) selectedToppings.add("Turkey Pepperoni");
        if (sausageCB.isSelected()) selectedToppings.add("Turkey Sausage");
        if (baconCB.isSelected()) selectedToppings.add("Turkey Bacon");
        if (greenPeppersCB.isSelected()) selectedToppings.add("Green Peppers");
        if (bananaPeppersCB.isSelected()) selectedToppings.add("Banana Peppers");
        if (onionsCB.isSelected()) selectedToppings.add("Onions");

        if (selectedToppings.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one topping.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double subTotal = currentBaseCost + (selectedToppings.size() * toppingCost);
        double tax = subTotal * TAX_RATE;
        double totalCost = subTotal + tax;

        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("_________________________________\n");
        orderSummary.append(" Shy's Pizza Palace for Turkey Lovers  \n");
        orderSummary.append("-----------------------------\n");
        orderSummary.append("Crust: ").append(crustType).append("\n");
        orderSummary.append("Size: ").append(size).append(" ($").append(String.format("%.2f", currentBaseCost)).append(")\n");
        orderSummary.append("**Toppings:**\n");
        for (String topping : selectedToppings) {
            orderSummary.append("- ").append(topping).append(" ($").append(String.format("%.2f", toppingCost)).append(")\n");
        }
        orderSummary.append("\n");
        orderSummary.append("Sub-total: $").append(String.format("%.2f", subTotal)).append("\n");
        orderSummary.append("Tax (").append(String.format("%.0f", TAX_RATE * 100)).append("%): $").append(String.format("%.2f", tax)).append("\n");
        orderSummary.append("Total: $").append(String.format("%.2f", totalCost)).append("\n");
        orderSummary.append("-----------------------------\n");
        orderSummary.append("Thank you for your order!\n");
        orderSummary.append("_________________________________\n");

        orderTA.setText(orderSummary.toString());
    }

    private void clearOrder() {
        crustGroup.clearSelection();
        regularCrustRB.setSelected(true); // Reset crust to default
        sizeCB.setSelectedIndex(0); // Reset size to the first option
        pepperoniCB.setSelected(false);
        sausageCB.setSelected(false);
        baconCB.setSelected(false);
        greenPeppersCB.setSelected(false);
        bananaPeppersCB.setSelected(false);
        onionsCB.setSelected(false);
        orderTA.setText("");
        updateBaseCost(); // Reset base cost
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaGUIFrame());
    }
}