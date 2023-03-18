package factorymethod;

public class ServiceFactory {
    public static IService getService(String service) {
        Class classRef = null;
        Object object = null;

        try {
            classRef = Class.forName("factorymethod." + service);
            object = classRef.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find service");
        }

        if(!(object instanceof IService<?>)) {
            throw new IllegalArgumentException("Invalid service");
        }

        return (IService) object;
    }
}
