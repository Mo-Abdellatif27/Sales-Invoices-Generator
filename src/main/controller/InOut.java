package main.controller;

import main.model.InvoiceHeader;
import main.model.InvoiceLine;
import main.model.InvoiceTableModel;
import main.view.InvoiceFrame;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InOut implements ActionListener{
    public InvoiceFrame InvFrObjLS;

    public InOut(InvoiceFrame InvFrObjLS){
        this.InvFrObjLS = InvFrObjLS;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if ("Load File".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(InvFrObjLS, "Please select the Invoice_Header AND the Invoice_Line files", "Two CSV Files Must be Selected!",JOptionPane.WARNING_MESSAGE);

            JFileChooser FChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
            FChooser.setFileFilter(filter);

            FChooser.setAcceptAllFileFilterUsed(false);

            FChooser.setMultiSelectionEnabled(true);

            int ValidValue = FChooser.showOpenDialog(InvFrObjLS);

            if(ValidValue == JFileChooser.APPROVE_OPTION){
                try{
                    File[] HeaderLnFiles = FChooser.getSelectedFiles();
                    if (HeaderLnFiles.length == 1 || HeaderLnFiles.length > 2 ){
                        HeaderLnFiles = null; JOptionPane.showMessageDialog(InvFrObjLS, "Please select the TWO SCV files TOGETHER", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        Path HeaderPath = Paths.get(HeaderLnFiles[0].getAbsolutePath());
                        Path LinePath = Paths.get(HeaderLnFiles[1].getAbsolutePath());

                        List <String> HeaderLnsArr = Files.readAllLines(HeaderPath);
                        List <String> LineArr = Files.readAllLines(LinePath);

                        ArrayList <InvoiceHeader> HeaderTbl = new ArrayList <> ();

                        for (String HeaderLnByLn : HeaderLnsArr ){
                            if (HeaderLnByLn.isEmpty() == true || HeaderLnFiles == null){
                                break;
                            }
                            if (StringUtils.countMatches(HeaderLnByLn, ",") != 2){
                                HeaderLnFiles = null; HeaderTbl = null; JOptionPane.showMessageDialog(InvFrObjLS, "Please Choose the right file", "Wrong Data",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            String [] HeaderLn = HeaderLnByLn.split(",");
                            String HeaderLn1 = HeaderLn[0];
                            String HeaderLn2 = HeaderLn[1];
                            String HeaderLn3 = HeaderLn[2];

                            int InvoiceNum = Integer.parseInt(HeaderLn1);
                            Date InvoiceDate = InvFrObjLS.dateFormat.parse(HeaderLn2);

                            InvoiceHeader headerTableObj = new InvoiceHeader(InvoiceNum, InvoiceDate, HeaderLn3);
                            HeaderTbl.add(headerTableObj);
                        }

                        InvFrObjLS.setInvTbl(HeaderTbl);

                        for (String LineByLine : LineArr ){
                            if (LineByLine.isEmpty() == true || HeaderLnFiles == null){break;}
                            if (StringUtils.countMatches(LineByLine, ",") != 3){HeaderLnFiles = null; HeaderTbl = null; JOptionPane.showMessageDialog(InvFrObjLS, "Please Choose the right file", "Wrong Data",JOptionPane.ERROR_MESSAGE); break;}
                            String [] LineParts = LineByLine.split(",");
                            String LinePart1 = LineParts[0];
                            String LinePart2 = LineParts[1];
                            String LinePart3 = LineParts[2];
                            String LinePart4 = LineParts[3];

                            int InvoiceNum = Integer.parseInt(LinePart1);
                            double ItemPrice = Double.parseDouble(LinePart3);
                            int ItemCount = Integer.parseInt(LinePart4);
                            InvoiceHeader InvoiceTblObj = InvFrObjLS.getInvTblObj(InvoiceNum);

                            InvoiceLine LnTable = new InvoiceLine(LinePart2, ItemPrice, ItemCount,InvoiceTblObj);
                            InvoiceTblObj.getInvoiceLine().add(LnTable);
                        }
                        if (HeaderTbl != null){
                            InvoiceTableModel InvHdTblObj = new InvoiceTableModel(HeaderTbl);
                            InvFrObjLS.setInvTblObj(InvHdTblObj);
                            InvFrObjLS.InvoiceTbl.setModel(InvHdTblObj);
                            InvFrObjLS.InvoiceTbl.validate();

                            InvFrObjLS.CreateNewInvoiceBtn.setEnabled(true);
                            InvFrObjLS.SaveFile.setEnabled(true);
                        }
                    }
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }

        if ("Save File".equals(e.getActionCommand())){
            ArrayList <InvoiceHeader> InvTblArr = InvFrObjLS.getInvTbl();

            JOptionPane.showMessageDialog(InvFrObjLS, "Please Save Header File First", "Header File Missing",JOptionPane.WARNING_MESSAGE);

            JFileChooser FChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
            FChooser.setFileFilter(filter);

            FChooser.setAcceptAllFileFilterUsed(false);

            int ValidlValue = FChooser.showSaveDialog(InvFrObjLS);

            if(ValidlValue == JFileChooser.APPROVE_OPTION){
                try{
                    File HeaderFile = FChooser.getSelectedFile();
                    if (HeaderFile != null && FilenameUtils.getExtension(HeaderFile.getName()).equalsIgnoreCase("csv")){
                    }
                    else if (HeaderFile != null && !FilenameUtils.getExtension(HeaderFile.getName()).equalsIgnoreCase("csv")){
                        HeaderFile = new File(HeaderFile.getParentFile(), FilenameUtils.getBaseName(HeaderFile.getName())+".csv");
                    }

                    FileWriter HeaderFileWriter = new FileWriter (HeaderFile);

                    String invHeaderLine = "";
                    String invLineLine = "";

                    for (InvoiceHeader InvTable : InvTblArr ){
                        invHeaderLine += InvTable.getInvoiceHeader() + "\n";

                        for(InvoiceLine detInv : InvTable.InvoiceLineTable){
                            invLineLine += detInv.getInvoiceLine() + "\n";
                        }
                    }

                    JOptionPane.showMessageDialog(InvFrObjLS, "Please Save Line File Now", "Line File Save",JOptionPane.WARNING_MESSAGE);
                    ValidlValue = FChooser.showSaveDialog(InvFrObjLS);
                    File LineFile = FChooser.getSelectedFile();
                    if (FilenameUtils.getExtension(LineFile.getName()).equalsIgnoreCase("csv")){
                    }
                    else if (!FilenameUtils.getExtension(LineFile.getName()).equalsIgnoreCase("csv")){
                        LineFile = new File(LineFile.getParentFile(), FilenameUtils.getBaseName(LineFile.getName())+".csv");
                    }

                    FileWriter LineFileWriter = new FileWriter (LineFile);

                    HeaderFileWriter.write(invHeaderLine);
                    LineFileWriter.write(invLineLine);

                    HeaderFileWriter.close();
                    LineFileWriter.close();
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }

}
