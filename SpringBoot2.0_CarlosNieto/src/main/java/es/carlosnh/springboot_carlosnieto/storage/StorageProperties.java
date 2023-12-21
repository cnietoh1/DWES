package es.carlosnh.springboot_carlosnieto.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="storage")
@Data
public class StorageProperties {

  private final String location = "upload-dir";

}