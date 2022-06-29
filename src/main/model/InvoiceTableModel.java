package main.model;

import main.view.InvoiceFrame;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoiceTableModel extends AbstractTableModel{
    public ArrayList <InvoiceHeader> InvoiceHeaderTable;

    public InvoiceTableModel(ArrayList <InvoiceHeader> InvoiceHeaderTable){
        this.InvoiceHeaderTable = InvoiceHeaderTable;
    }

    public ArrayList <InvoiceHeader> getInvoiceHeader()
    {
        return this.InvoiceHeaderTable;
    }

    @Override
    public int getRowCount()
    {
        return InvoiceHeaderTable.size();
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0 :
                return Integer.class;

            case 1 :
                return String.class;

            case 2 :
                return String.class;

            case 3:
                return Double.class;
        }
        return Class.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        InvoiceHeader InvHdTable = InvoiceHeaderTable.get(rowIndex);

        switch (columnIndex)
        {
            case 0 :
                return InvHdTable.InvoiceNumber;

            case 1 :
                return InvoiceFrame.dateFormat.format(InvHdTable.InvoiceDate);

            case 2 :
                return InvHdTable.CustomerName;

            case 3 :
                return InvHdTable.getInvoiceTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0 :
                return "No.";

            case 1 :
                return "Date";

            case 2 :
                return "Customer";

            case 3 :
                return "Total";
        }
        return "";
    }
}
