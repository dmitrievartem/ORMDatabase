package ormdatabase.entity;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ReboundStack {
    List<Shim> stack;

    public ReboundStack() {
        stack = new ArrayList<>(List.of(new Shim("1", "1", "0")));
    }

    public ReboundStack(List<Shim> stack) {
        this.stack = stack;
    }

    public ReboundStack(ReboundStack reboundStack) {
        this.stack = new ArrayList<>();
        for (Shim shim : reboundStack.stack) {
            this.stack.add(new Shim(shim));
        }
    }

    public List<Shim> getStack() {
        return stack;
    }

}
