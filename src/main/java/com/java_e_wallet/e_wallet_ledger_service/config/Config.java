package com.java_e_wallet.e_wallet_ledger_service.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {
    private String KafkaAddress;
    private String KafkaTransactionTopicName;

    private static Config configInstance;

    private Config() {
    }

    private Optional<JSONObject> readConfig() {
        JSONParser parser = new JSONParser();

        String configFilePath = System.getenv("JAVA_E_WALLET_LEDGER_CONF");

        try {
            JSONObject data = (JSONObject) parser.parse(new FileReader(configFilePath));

            return Optional.of(data);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return Optional.empty();

        } catch (IOException ex) {
            ex.printStackTrace();
            return Optional.empty();

        } catch (ParseException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public static void Init() {
        if (configInstance == null) {
            configInstance = new Config();
        }

        Optional<JSONObject> data = configInstance.readConfig();
        if (!data.isPresent()) {
            System.err.println("Failed to load configuration.");
        }

        JSONObject configData = data.get();

        configInstance.KafkaAddress = (String) configData.get("kafka_address");
        configInstance.KafkaTransactionTopicName = (String) configData.get("kafka_transaction_topic_name");
    }

    public static Config getConfigInstance() {
        if (configInstance == null) {
            throw new IllegalStateException("Config must be initialized first by calling Init().");
        }
        return configInstance;
    }

    public static String getKafkaAddress() {
        if (configInstance == null) {
            throw new IllegalStateException("Config must be initialized first by calling Init().");
        }

        return configInstance.KafkaAddress;
    }

    public static String getKafkaTransactionTopicName() {
        if (configInstance == null) {
            throw new IllegalStateException("Config must be initialized first by calling Init().");
        }

        return configInstance.KafkaTransactionTopicName;
    }
}
