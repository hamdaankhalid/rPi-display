package com.hamdaankhalid.rpi.converter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class StringConverter {
    private final int rows;
    private final int cols;

    public StringConverter(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * @param sentence
     * @return a 2 d matrix representing the word left and right justified on top of a matrix
     */
    public List<String> convert(String sentence) throws InvalidParameterException {
        List<String> rowsToAdd = splitSentenceIntoRows(sentence);

        if (rowsToAdd.size() > rows) {
            throw new InvalidParameterException("Sentence to convert is too big for given matrix size");
        }

        for (int i = 0; i < rowsToAdd.size(); i++) {
            String row = rowsToAdd.get(i);

            int rowLength = row.length();
            int paddingToSatisfy = cols - rowLength;
            boolean padRight = true;

            while (paddingToSatisfy > 0) {

                if (!padRight) {
                    row = " " + row;
                    rowsToAdd.set(i, row);
                } else {
                    row = row + " ";
                    rowsToAdd.set(i, row);
                }
                padRight = !padRight;
                paddingToSatisfy--;
            }
        }


        // Now each row just needs to be converted into scale of column
        return rowsToAdd;
    }

    private List<String> splitSentenceIntoRows(String sentence) {
        String[] splitBySpace = sentence.split(" ");


        int limit = cols;
        int occupied = 0;

        List<String> sentenceByRows = new ArrayList<String>();

        String rowToAdd = "";

        int i = 0;
        while (i < splitBySpace.length) {

            String word = splitBySpace[i];

            // the +1 is for the first redundant space that we will later on remove
            if (word.length() + occupied + 1 <= limit) {
                rowToAdd += ' ' + word;
                occupied += rowToAdd.length();
                i++;
            } else {
                sentenceByRows.add(rowToAdd);
                rowToAdd = "";
                occupied = 0;
            }
        }

        if (rowToAdd != "") {
            sentenceByRows.add(rowToAdd);
        }

        // remove the redundant space
        for(int q = 0; q < sentenceByRows.size(); q++) {
            String redundantSpaceRemoved = sentenceByRows.get(q).substring(1);

            sentenceByRows.set(q, redundantSpaceRemoved);
        }

        return sentenceByRows;
    }
}
