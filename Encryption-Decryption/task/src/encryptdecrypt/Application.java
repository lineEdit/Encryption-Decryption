package encryptdecrypt;

import java.util.Scanner;

class Application {
    private final Parser parser;
    private final Coder coder;

    public Application(String[] args) {
        parser = new Parser(args);
        coder = new Coder();
        switch (parser.getMode()) {
            case ENC:
                coder.setCrypt(new Encrypt());
                break;
            case DEC:
                coder.setCrypt(new Decrypt());
                break;
        }
    }

    public void action() {
        if (parser.getInFileName().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("data = ");
            parser.setData(scanner.nextLine());
            if (parser.getKey() == null) {
                parser.setKey(scanner.nextInt());
            }
//            scanner.close();
        } else {
            parser.readFileExceptionHandling();
        }

        char[] out = coder.action(parser.getData().toCharArray(), parser.getKey(), parser.getAlg());

        if (parser.getOutFileName().isEmpty()) {
            System.out.println(out);
        } else {
            parser.writeFileExceptionHandling(out);
        }
    }
}
