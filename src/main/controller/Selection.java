package main.controller;

import main.model.InvoiceHeader;
import main.model.InvoiceLine;
import main.view.InvoiceFrame;
import main.view.LineDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection implements ActionListener, ListSelectionListener{
    public InvoiceFrame InvFrObjS;
    private LineDialog InvLnObjS;

    public Selection (InvoiceFrame InvFrObjS){
        this.InvFrObjS = InvFrObjS;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if ("Add New Item".equals(e.getActionCommand())){
            InvLnObjS = new LineDialog(this);
            InvLnObjS.setVisible(true);
        }

        if("OK".equals(e.getActionCommand())){
            String ItemName = InvLnObjS.ItemNameTxt.getText();
            String ItemCountCheck = InvLnObjS.ItemCountSpn.getValue().toString();
            String ItemPriceCheck = InvLnObjS.ItemPriceTxt.getText();

            InvLnObjS.setVisible(false);
            InvLnObjS.dispose();
            InvLnObjS = null;

            if (ItemName.trim().length() == 0){
                JOptionPane.showMessageDialog(InvFrObjS, "Please enter the item name", "Empty Entry!",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    int ItemCount = Integer.parseInt(ItemCountCheck);
                    double ItemPrice = Double.parseDouble(ItemPriceCheck);

                    int InvoicesTableIndex = InvFrObjS.InvoiceTbl.getSelectedRow();
                    InvoiceHeader invTblObj = InvFrObjS.getInvTbl().get(InvoicesTableIndex);
                    InvoiceLine detInvObj = new InvoiceLine(ItemName, ItemPrice, ItemCount, invTblObj);

                    InvFrObjS.LnTblObj.InvoiceLineTable.add(detInvObj);
                    InvFrObjS.LnTblObj.fireTableDataChanged();
                    InvFrObjS.LnTblObj.fireTableDataChanged();

                    InvFrObjS.InvoiceTotalLbl.setText("" +invTblObj.getInvoiceTotal());

                    InvFrObjS.InvoiceTbl.setRowSelectionInterval(InvoicesTableIndex, InvoicesTableIndex);

                    int lastItemIndex = InvFrObjS.LnTblObj.InvoiceLineTable.indexOf(detInvObj);
                    InvFrObjS.LineTbl.setRowSelectionInterval(lastItemIndex , lastItemIndex);
                }
                catch (NumberFormatException numForEx){
                    JOptionPane.showMessageDialog(InvFrObjS, "Please enter the price of the item", "Empty item price",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if("Cancel".equals(e.getActionCommand())){
            InvLnObjS.setVisible(false);
            InvLnObjS.dispose();
            InvLnObjS = null;
        }


        else if ("Remove Selected Item".equals(e.getActionCommand())){
            int DetInvIndex = InvFrObjS.LineTbl.getSelectedRow();

            if (DetInvIndex >= 0){
                int DetIndex = InvFrObjS.LineTbl.getSelectedRow();
                int InvIndex = InvFrObjS.InvoiceTbl.getSelectedRow();

                InvoiceLine DetSelectedLn = InvFrObjS.LnTblObj.InvoiceLineTable.get(DetIndex);
                InvFrObjS.LnTblObj.InvoiceLineTable.remove(DetIndex);
                InvFrObjS.LnTblObj.fireTableDataChanged();
                InvFrObjS.LnTblObj.fireTableDataChanged();

                InvFrObjS.InvoiceTotalLbl.setText("" + DetSelectedLn.InvHdObj.getInvoiceTotal());

                InvFrObjS.InvoiceTbl.setRowSelectionInterval(InvIndex, InvIndex);

                int LastItemIndex = InvFrObjS.LnTblObj.InvoiceLineTable.size() - 1;
                if (LastItemIndex >= 0){
                    InvFrObjS.LineTbl.setRowSelectionInterval(LastItemIndex , LastItemIndex);
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e){
        int DetInvIndex = InvFrObjS.LineTbl.getSelectedRow();

        if (DetInvIndex >= 0){
            InvFrObjS.RemoveItemBtn.setEnabled(true);
        }
        else if (DetInvIndex < 0){
            InvFrObjS.RemoveItemBtn.setEnabled(false);
        }

        if (InvFrObjS.LnTblObj.InvoiceLineTable.isEmpty()){
            InvFrObjS.RemoveItemBtn.setEnabled(false);
        }
    }
}
