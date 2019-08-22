package encryptdecrypt;

enum Algorithm {
    UNICODE,
    SHIFT
}

enum Mode {
    DEC,
    ENC
}

interface Crypt {
    char[] action(char[] data, int key, Algorithm algorithm);
}

class Encrypt implements Crypt {

    @Override
    public char[] action(char[] data, int key, Algorithm algorithm) {
        char[] out = new char[data.length];

        for (int i = 0; i < data.length; i++) {
            switch (algorithm) {
                case SHIFT:
                    char c = (char) (Character.toLowerCase(data[i]) + key);
                    if ('a' <= data[i] && data[i] <= 'z') {
                        out[i] = (char) (c > 'z' ? c - 26 : c);
                    }
                    break;
                case UNICODE:
                    out[i] = (char) (data[i] + key);
                    break;
            }
        }
        return out;
    }
}

class Decrypt implements Crypt {

    @Override
    public char[] action(char[] data, int key, Algorithm algorithm) {
        char[] out = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            switch (algorithm) {
                case SHIFT:
                    char c = (char) (Character.toLowerCase(data[i]) - key);
                    if ('a' <= data[i] && data[i] <= 'z') {
                        out[i] = (char) (c < 'a' ? c + 26 : c);
                    }
                    break;
                case UNICODE:
                    out[i] = (char) (data[i] - key);
                    break;
            }
        }
        return out;
    }
}

class Coder {
    private Crypt crypt;

    public void setCrypt(Crypt crypt) {
        this.crypt = crypt;
    }

    public char[] action(char[] data, int key, Algorithm algorithm) {
        return this.crypt.action(data, key, algorithm);
    }
}