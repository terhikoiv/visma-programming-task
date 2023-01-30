/**
 * Class called "Client" which uses Identity-class method .validate() to
 * identify what kind of request is given.
 */

public class Client {
    public static void main(String[] args) throws Exception {

        Identity login = new Identity("visma-identity://login?source=severa");
        Identity confirm = new Identity("visma-identity://confirm?source=netvisor&paymentnumber=102226");
        Identity sign = new Identity("visma-identity://sign?source=vismasign&documentid=105ab44");

        for (Object obj : login.validate()) {
            System.out.println(obj);
        }
        System.out.println();

        for (Object obj : confirm.validate()) {
            System.out.println(obj);
        }
        System.out.println();

        for (Object obj : sign.validate()) {
            System.out.println(obj);
        }

    }
}
