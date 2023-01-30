import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Identity {
    String uri;
    String scheme;
    String path;
    String query;

    public Identity(String URI) {
        uri = URI;
    }

    /**
     * This method is used to slice uri to schema, path and parameters.
     * Validates schema and path. If either one is incorrect returns empty list.
     * 
     * @return a list of uri and parameters as key-value pairs
     * @throws Exception
     */

    public ArrayList<Object> validate() throws Exception {
        // using java class URI
        URI address = new URI(uri);

        scheme = address.getScheme();
        path = address.getAuthority();
        query = address.getQuery();

        ArrayList<Object> list = new ArrayList<>();

        if (scheme.equals("visma-identity")) {
            if (path.equals("login")) {
                if (!loginMethod().isEmpty()) {
                    list.add(address);
                    list.add(loginMethod());
                    return list;
                } else {
                    System.out.println("Parameters are incorrect");
                }

            } else if (path.equals("confirm")) {
                if (!confirmMethod().isEmpty()) {
                    list.add(address);
                    list.add(confirmMethod());
                    return list;
                } else {
                    System.out.println("Parameters are incorrect");
                }

            } else if (path.equals("sign")) {
                if (!signMethod().isEmpty()) {
                    list.add(address);
                    list.add(signMethod());
                    return list;
                } else {
                    System.out.println("Parameters are incorrect");
                }

            } else {
                System.out.println("Path is incorrect");
            }
        } else {
            System.out.println("Schema is incorrect");
        }
        return list;
    }

    /**
     * This method is used to deal with "login" -path.
     * Method slices parameters to key-value pairs.
     * 
     * @return hashmap with parameters if parameters are correct, else returns empty
     *         hashmap
     */
    public HashMap<String, String> loginMethod() {
        HashMap<String, String> values = new HashMap<>();
        String[] keyValue = query.split("=");
        if (keyValue[0].equals("source")) {
            values.put(keyValue[0], keyValue[1]);
            return values;
        }
        return values;
    }

    /**
     * This method is used to handle "confirm"-path.
     * Method slices parameters to key-value pairs.
     * 
     * @return hashmap with parameters if parameters are correct, else returns empty
     *         hashmap
     */
    public HashMap<String, Object> confirmMethod() {
        HashMap<String, Object> values = new HashMap<>();
        String[] parameters = query.split("&");
        for (String params : parameters) {
            String[] keyValue = params.split("=");
            if (keyValue[0].equals("source") || keyValue[0].equals("paymentnumber")) {
                values.put(keyValue[0], keyValue[1]);
            } else {
                return values;
            }
        }
        return values;
    }

    /**
     * This method is used to handle "sign"-path.
     * Method slices parameters to key-value pairs.
     * 
     * @return hashmap with parameters if parameters are correct, else returns empty
     *         hashmap
     */
    public HashMap<String, String> signMethod() {
        HashMap<String, String> values = new HashMap<>();
        String[] parameters = query.split("&");
        for (String params : parameters) {
            String[] keyValue = params.split("=");
            if (keyValue[0].equals("source") || keyValue[0].equals("documentid")) {
                values.put(keyValue[0], keyValue[1]);
            } else {
                return values;
            }
        }
        return values;
    }

}