package Zim.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Laxton-Joe on 2017/8/15.
 */

@Document(collection = "FingerprintTemplate")
public class FingerprintTemplate extends BinaryEntity {
}
