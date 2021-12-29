package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class CompressionStack {
    List<Shim> stack;

    public CompressionStack() {
    }

    public CompressionStack(List<Shim> stack) {
        this.stack = stack;
    }

    public List<Shim> getStack() {
        return stack;
    }

}
