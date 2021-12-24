package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class ReboundStack {
    List<Shim> stack;

    public List<Shim> getStack() {
        return stack;
    }

    public void setStack(List<Shim> stack) {
        this.stack = stack;
    }
}
