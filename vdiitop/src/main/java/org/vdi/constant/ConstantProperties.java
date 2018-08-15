package org.vdi.constant;

public final class ConstantProperties {

	public ConstantProperties() {

	}

	public static final String ITOP_WEB_HEADER_USER_AGENT = "User-Agent";
	public static final String ITOP_WEB_HEADER_ACCEPT = "Accept";
	public static final String ITOP_WEB_HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String ITOP_WEB_HEADER_ACCEPT_LANGUAGE = "Accept-Language";
	public static final String ITOP_WEB_HEADER_AUTHORIZATION = "Authorization";
	public static final String ITOP_WEB_HEADER_CACHE_CONTROL = "Cache-Control";
	public static final String ITOP_WEB_HEADER_COOKIE = "Cookie";
	public static final String ITOP_WEB_HEADER_SET_COOKIE = "Set-Cookie";
	public static final String ITOP_WEB_HEADER_HOST = "Host";
	public static final String ITOP_WEB_HEADER_PROXY_CONNECTION = "Proxy-Connection";
	public static final String ITOP_WEB_HEADER_UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests";

	public static final String ITOP_WEB_HEADER_USER_AGENT_VAL = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36";
	public static final String ITOP_WEB_HEADER_ACCEPT_VAL = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
	public static final String ITOP_WEB_HEADER_ACCEPT_ENCODING_VAL = "gzip, deflate";
	public static final String ITOP_WEB_HEADER_ACCEPT_LANGUAGE_VAL = "en-US,en;q=0.9,id;q=0.8,ms;q=0.7";
	public static final String ITOP_WEB_HEADER_ACCEPT_CACHE_CONTROL_VAL = "max-age=0";
	public static final String ITOP_WEB_HEADER_ACCEPT_PROXY_CONNECTION_VAL = "keep-alive";
	public static final String ITOP_WEB_HEADER_ACCEPT_UPGRADE_INSECURE_REQUESTS_VAL = "1";

	public static final String PROPS_ITOP_WEB_HOST = "itop.web.host";
	public static final String PROPS_ITOP_WEB_HOST_URL = "itop.web.host.url";
	public static final String PROPS_ITOP_WEB_AUTHORIZATION = "itop.web.authorization";

	public static final String PROPS_HTTP_TIMEOUT = "http.timeout";
	public static final String PROPS_HTTP_MAXPOOL = "http.maxpool";
	public static final String PROPS_HTTP_MAXPERROUTE = "http.maxperroute";

	public static final String PROPS_HTTP_PROXY_HOST = "http.proxy.host";
	public static final String PROPS_HTTP_PROXY_PORT = "http.proxy.port";
	public static final String PROPS_HTTP_PROXY_SCHEME = "http.proxy.scheme";

	public static final String PROPS_HTTP_URL_ITOP_SD = "http.url.itop.sd";
	public static final String PROPS_HTTP_URL_ITOP_SHIFT1 = "http.url.itop.shift1";

	// DB config
	public static final String PROPERTY_NAME_DB_DRIVER_CLASS = "db.driver";
	public static final String PROPERTY_NAME_DB_PASSWORD = "db.password";
	public static final String PROPERTY_NAME_DB_URL = "db.url";
	public static final String PROPERTY_NAME_DB_USER = "db.username";
	public static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	public static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String[] PROPERTY_NAME_ENTITY_PACKAGE = { "org.vdi.model" };
	public static final String PROPERTY_NAME_HIBERNATE_ID_NEW_GENERATOR_MAPPINGS = "hibernate.id.new_generator_mappings";
	// db tweak
	public static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
	public static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_VERSIONED_DATA = "hibernate.jdbc.batch_versioned_data";
	public static final String PROPERTY_NAME_HIBERNATE_ORDER_INSERTS = "hibernate.order_inserts";
	public static final String PROPERTY_NAME_HIBERNATE_ORDER_UPDATES = "hibernate.order_updates";
	public static final String PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
	public static final String PROPERTY_NAME_HIBERNATE_CONNECTION_AUTOCOMMIT = "hibernate.connection.autocommit";

}
