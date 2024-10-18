package tebogomkhize.projects.atmsimulation.account.model;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class AccStatementDocument {
    Document document;

    public void openDocument() {
        this.document.open();
    }

    public void closeDocument() {
        this.document.close();
    }

    /**
     * Creates pdf statement containing transactions.
     * @param transactions used to create pdf statement.
     * @return byte array representing pdf statement.
     */
    public byte[] createStatement(
        String accNum, LocalDate threeMonthsPrior, LocalDate currentDate,
        List<Transaction> transactions) throws DocumentException {

        this.document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // document linked to a pdf writer which writes content to file.
        PdfWriter.getInstance(this.document, out);
        openDocument();

        document.add(accNumPeriodParagraph(accNum, threeMonthsPrior,
            currentDate));

        PdfPTable table = createTable(transactions);
        document.add(table);
        closeDocument();

        byte[] pdfBytes = out.toByteArray();

        return pdfBytes;
    }

    /**
     * Creates a Paragraph to be included in the pdf file displaying
     * account number and period of transactions included in pdf file.
     * @param accNum Account number whom pdf file is generated for.
     * @param threeMonthsPrior Start date of transactions included in pdf.
     * @param currentDate end date of transactions included in pdf.
     * @return Paragraph instance containing text.
     */
    private Paragraph accNumPeriodParagraph(
        String accNum, LocalDate threeMonthsPrior,
        LocalDate currentDate) {

        String text = "Account Number: " + accNum + ".\nPeriod: " +
            threeMonthsPrior + " to " + currentDate + ".\n\n\n\n\n\n";

        return new Paragraph(text);
    }

    /**
     * Creates table to be contained within Pdf.
     * @param transactions used to populate pdf.
     * @return table (PdfTable) containing relevant data.
     */
    public PdfPTable createTable(List<Transaction> transactions) {
        PdfPTable table = new PdfPTable(4);
        addTableHeader(table);
        addRows(table, transactions);

        return table;
    }

    /**
     * Header row added to the pdf table.
     * @param table table (PdfTable) containing header.
     */
    public void addTableHeader(PdfPTable table) {
        Stream.of("Date", "Transaction Type", "Amount", "Balance")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.BLUE);

                Font whiteFont = new Font(Font.FontFamily.HELVETICA, 12,
                    Font.NORMAL, BaseColor.WHITE);

                header.setPhrase(new Phrase(columnTitle, whiteFont));

                table.addCell(header);
        });
    }

    /**
     * Adds rows to table (PdfTable) and populates rows with data.
     * @param table table which will have data added to.
     * @param transactions transactions (contains data such as date, type and
     * amount) used to populate table.
     */
    public void addRows(PdfPTable table, List<Transaction> transactions) {
        for (Transaction transaction: transactions) {
            table.addCell(transaction.getDate().toString());
            table.addCell(transaction.getType());
            table.addCell(String.valueOf(transaction.getAmount()));
            table.addCell(String.valueOf(transaction.getPostTransBal()));
        }
    }
}
