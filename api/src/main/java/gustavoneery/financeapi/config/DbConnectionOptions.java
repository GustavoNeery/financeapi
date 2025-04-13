package gustavoneery.financeapi.config;

public record DbConnectionOptions(
        String dbUrl,
        String dbUser,
        String dbPassword
) {
}
