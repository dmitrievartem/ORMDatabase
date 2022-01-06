package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ReboundStack {
    List<Shim> stack;

    public ReboundStack() {
    }

    public ReboundStack(List<Shim> stack) {
        this.stack = stack;
    }

    public ReboundStack(ReboundStack reboundStack) {
        this.stack = new ArrayList<>(reboundStack.stack);
    }

    public List<Shim> getStack() {
        return stack;
    }

}
