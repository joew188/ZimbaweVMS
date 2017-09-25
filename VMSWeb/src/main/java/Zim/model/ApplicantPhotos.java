package Zim.model;

/**
 * Created by Laxton-Joe on 2017/8/15.
 */

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ApplicantPhotos")
public class ApplicantPhotos  extends BinaryEntity{
}
