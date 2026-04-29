package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PositiveDecimalFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
            throws BadLocationException {
        String current = fb.getDocument().getText(0, fb.getDocument().getLength());
        String result = current.substring(0, offset) + text + current.substring(offset);
        if (isValid(result)) {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
            throws BadLocationException {
        String current = fb.getDocument().getText(0, fb.getDocument().getLength());
        String result = current.substring(0, offset) + text + current.substring(offset + length);
        if (isValid(result)) {
            super.replace(fb, offset, length, text, attr);
        }
    }

    private boolean isValid(String text) {
        if (text.isEmpty()) return true;
        return text.matches("\\d{0,4}(\\.\\d{0,1})?");
    }
}