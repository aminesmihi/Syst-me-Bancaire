package net.smihi.accountservice.commands.exception;

public class NegativeInitialBalanceException extends RuntimeException {
    public NegativeInitialBalanceException(String s) {
        super(s);
    }
}
