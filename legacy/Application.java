package uni.pu.fmi;

public class Application {
    public static void persistTransaction(EntityManager manager, List<Object> objects) {
        manager.getTransaction().begin();
        for (Object o : objects) {
            manager.persist(o);
        }
        manager.getTransaction().commit();
    }

    public static void main(String[] args) {

        System.out.println(System.getenv("DB_URL"));
        try (EntityManagerFactory h2Factory = Persistence.createEntityManagerFactory("h2-unit")) {
            try (EntityManager h2Manager = h2Factory.createEntityManager()) {
                h2Manager.getTransaction().begin();
                h2Manager.persist(new Stock("Nasdaq", "NDQ"));
                h2Manager.getTransaction().commit();
            }
        }
        Stock nasdaq = new Stock("Nasdaq", "NSD");
        Stock spx = new Stock("S&P500", "SPX");

        Investor investor1 = new Investor("Todor", "Arnaudov", LocalDate.of(2003, 1, 15), LocalDate.of(2022, 1, 15));
        Investor investor2 = new Investor("Ivaylo", "Iliev", LocalDate.of(2003, 5, 7), LocalDate.of(2022, 1, 15));
        Broker broker = new Broker("Mr", "Watchings", LocalDate.of(1975, 7, 23), "1234567880", LocalDate.of(2022, 1, 15));

        Trade trade = new Trade(investor2, spx, 2.0, LocalDateTime.now());
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("supabase-unit");
             EntityManager manager = factory.createEntityManager()) {
            manager.getTransaction().begin();
            manager.find(Trade.class, 1L).setBroker(manager.find(Broker.class, 1L));
            manager.getTransaction().commit();
            manager.getTransaction().begin();
            manager.remove(manager.find(Stock.class, 1102));
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            manager.find(Investor.class, 2L).addStock(nasdaq);
            manager.getTransaction().commit();


            persistTransaction(manager, List.of(trade));
        }
    }
}
