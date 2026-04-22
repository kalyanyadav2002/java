
package pattern.factory;

import model.Member;

public class MemberFactory {
    public static Member createMember(int id, String name, String password){
        return new Member(id,name,password);
    }
}
