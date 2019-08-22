package encryptdecrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class Parser {
    private String data;
    private String inFileName;
    private String outFileName;
    private Integer key;
    private Mode mode;
    private Algorithm alg;

    public Parser(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-mode":
                        i++;
                        mode = Mode.valueOf(args[i].toUpperCase());
                        break;
                    case "-key":
                        i++;
                        try {
                            key = Integer.parseInt(args[i]);
                        } catch (Exception e) {
                            key = 0;
                            System.out.println("Key incorrect = " + args[i]);
                        }
                        break;
                    case "-data":
                        i++;
                        data = args[i];
                        break;
                    case "-in":
                        i++;
                        inFileName = args[i];
                        break;
                    case "-out":
                        i++;
                        outFileName = args[i];
                        break;
                    case "-alg":
                        i++;
                        alg = Algorithm.valueOf(args[i].toUpperCase());
                        break;
                    default:
                        break;
                }
            }
        } else {
            data = "";
            inFileName = "";
            outFileName = "";
            key = null;
            mode = Mode.ENC;
            alg = Algorithm.SHIFT;
        }
    }

    public String readFileExceptionHandling(String fileName) {
        String result = "";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            System.out.println("There is no such file, " + e.getMessage());
        }
        return result;
    }

    public void readFileExceptionHandling() {
        data = readFileExceptionHandling(inFileName);
    }

    public void writeFileExceptionHandling(String fileName, char[] output) {
            try (
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(fileName), StandardCharsets.UTF_8
                            )
                    );
            )
            {
                writer.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void writeFileExceptionHandling(char[] output) {
        writeFileExceptionHandling(outFileName, output);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInFileName() {
        return inFileName;
    }

    public void setInFileName(String inFileName) {
        this.inFileName = inFileName;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Algorithm getAlg() {
        return alg;
    }

    public void setAlg(Algorithm alg) {
        this.alg = alg;
    }
}