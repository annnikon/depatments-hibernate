# depatments-hibernate


 private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null || sessionFactory.isClosed()) {
            try {
                StandardServiceRegistry registry =
                        new StandardServiceRegistryBuilder()
                                .configure().build();
                Metadata metadata = new MetadataSources(registry)
                        .getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();


            } catch (Exception e) {
                throw new RuntimeException("Could not init Hibernate config",e);
            }
        }

        return sessionFactory;
    }
