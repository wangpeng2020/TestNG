import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import java.util.HashMap;


public class JunitOgnl extends AssertWithMatcher {

    private OgnlContext ognlContext;
    public JunitOgnl() {
        this.ognlContext = new OgnlContext(new DefaultMemberAccess(), null, null, new HashMap<>());
        //this.ognlContext.put("match", new CoreMatchers());
        //this.ognlContext.setRoot(new CoreMatchers());
    }

    public Object ognl(String expression) throws OgnlException {
        return Ognl.getValue(expression, ognlContext, this);
    }
}

