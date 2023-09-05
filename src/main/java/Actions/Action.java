package Actions;

public class Action {
    private TurnAction turnAction = new TurnAction();
    private InitAction initAction = new InitAction();

    public TurnAction getTurnAction() {
        return turnAction;
    }

    public InitAction getInitAction() {
        return initAction;
    }
}
