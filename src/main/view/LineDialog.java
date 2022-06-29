package main.view;

import main.controller.Selection;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;

public class LineDialog extends JDialog {
    public JLabel ItemNameLbl;
    public JTextField ItemNameTxt;
    public JLabel ItemCountLbl;
    public JSpinner ItemCountSpn;
    public JLabel ItemPriceLbl;
    public JTextField ItemPriceTxt;
    public JButton Ok;
    public JButton Cancel;

    public LineDialog(Selection selection){
        ItemNameLbl = new JLabel("       Item Name: ");
        ItemNameTxt =new JTextField(15);
        ItemCountLbl = new JLabel("       Item Count: ");
        SpinnerModel Spn = new SpinnerNumberModel(1,1,1000,1);
        ItemCountSpn = new JSpinner(Spn);
        JFormattedTextField Txt = ((JSpinner.NumberEditor) ItemCountSpn.getEditor()).getTextField();
        ((NumberFormatter) Txt.getFormatter()).setAllowsInvalid(false);
        ItemPriceLbl = new JLabel("       Item Price: ");
        ItemPriceTxt =new JTextField(15);
        DecimalFormat Format = new DecimalFormat("0.00");
        Format.setGroupingUsed(false);
        NumberFormatter Formatter = new NumberFormatter(Format);
        Formatter.setValueClass(Double.class);
        Formatter.setMinimum(1.0);
        Formatter.setMaximum(Double.MAX_VALUE);
        Formatter.setAllowsInvalid(false);
        Formatter.setCommitsOnValidEdit(true);
        ItemPriceTxt = new JFormattedTextField(Formatter);
        Ok = new JButton("OK");
        Ok.setActionCommand("OK");
        Ok.addActionListener(selection);
        Cancel = new JButton("Cancel");
        Cancel.setActionCommand("Cancel");
        Cancel.addActionListener(selection);

        setLayout(new GridLayout(4,2));
        add(ItemNameLbl);
        add(ItemNameTxt);
        add(ItemCountLbl);
        add(ItemCountSpn);
        add(ItemPriceLbl);
        add(ItemPriceTxt);
        add(Ok);
        add(Cancel);
        pack();
        setTitle("Items");
        setLocationRelativeTo(null);
    }
}
