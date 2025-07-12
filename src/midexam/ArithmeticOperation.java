package midexam;


public abstract class ArithmeticOperation {
    private final String operationName;

    public ArithmeticOperation(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    
    public abstract String getQuestion(int a, int b);
    public abstract int calculate(int a, int b);
}

class Addition extends ArithmeticOperation {
    public Addition() {
        super("Penjumlahan");
    }

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }

    @Override
    public String getQuestion(int a, int b) {
        return String.format("Berapa hasil dari %d + %d?", a, b);
    }
}

class Subtraction extends ArithmeticOperation {
    public Subtraction() {
        super("Pengurangan");
    }

    @Override
    public int calculate(int a, int b) {
        return a - b;
    }

    @Override
    public String getQuestion(int a, int b) {
        return String.format("Berapa hasil dari %d - %d?", a, b);
    }
}