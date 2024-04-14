package principles.interface_segregation;

public class MultiFuncMachine implements Printer, Scanner {
    public void print() {
        System.out.println("Multi Func Machine Print");
    }

    public void scan() {
        System.out.println("Multi Func Machine Scan");
    }
}

