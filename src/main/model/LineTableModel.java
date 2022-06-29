package main.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LineTableModel extends AbstractTableModel {
    public ArrayList <InvoiceLine> InvoiceLineTable;

    public LineTableModel(ArrayList <InvoiceLine> InvoiceLineTable){
        this.InvoiceLineTable = InvoiceLineTable;
    }

    @Override
    public int getRowCount(){
            if (InvoiceLineTable == null)
            {
            return 0;
            }
            else {return InvoiceLineTable.size();}
            }

    @Override
    public int getColumnCount(){
            return 4;
            }

    @Override
    public Class<?> getColumnClass(int ColumnIndex){
            switch (ColumnIndex) {
            case 0 :
            return String.class;

                case 1 :
                        return Double.class;

                case 2 :
                        return Integer.class;

                case 3:
                        return Double.class;
            }
                    return Class.class;
        }

    @Override
    public Object getValueAt(int RowIndex, int ColumnIndex){
            if (InvoiceLineTable == null){
                return "";
            }
            else{
            InvoiceLine InvLnObj = InvoiceLineTable.get(RowIndex);
            switch (ColumnIndex){
            case 0 :
            return InvLnObj.ItemName;
            case 1 :
            return InvLnObj.ItemPrice;

            case 2 :
            return InvLnObj.ItemCount;

            case 3 :
            return InvLnObj.PriceTotal;
            }
            return "";
            }
            }

    @Override
    public String getColumnName (int column){
            switch (column){
            case 0 :
            return "Item Name";

            case 1 :
            return "Item Price";

            case 2 :
            return "Count";

            case 3 :
            return "Item Total";
            }
            return "";
            }
}
