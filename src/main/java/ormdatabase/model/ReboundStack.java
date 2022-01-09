package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ReboundStack implements Cloneable {
    List<Shim> stack;

    public ReboundStack() {
        stack = new ArrayList<>(List.of(new Shim("0", "0", "0")));
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

    @Override
    public ReboundStack clone() {
        try {
            ReboundStack clone = (ReboundStack) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
//            clone.stack = new ArrayList<>(clone.stack);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
