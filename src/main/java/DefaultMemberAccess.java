import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Map;

import ognl.MemberAccess;

public class DefaultMemberAccess implements MemberAccess {

    @Override
    @SuppressWarnings("rawtypes")
    public Object setup(Map context, Object target, Member member, String propertyName) {
        return null;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void restore(Map context, Object target, Member member, String propertyName, Object state) {
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
        return Modifier.isPublic(member.getModifiers());
    }

}