package com.xochitl.helloWorld.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.xochitl.helloWorld.model.Cloth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ClothRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Cloth addCloth(Cloth cloth) {
        log.info("Method SaveCloth");
        dynamoDBMapper.save(cloth);
        return cloth;
    }

    public Optional<Cloth> getClothById(String cloth_id) {
        log.info("Method getClothById");
        Cloth cloth = null;
        Map<String, AttributeValue> eav= new HashMap<>();
        eav.put(":cloth_id", new AttributeValue().withS(cloth_id));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("cloth_id=:cloth_id")
                .withExpressionAttributeValues(eav);
        List<Cloth> useResult = dynamoDBMapper.scan(Cloth.class, scanExpression);
        if(!useResult.isEmpty()) {
            cloth = useResult.get(0);
        }
        return Optional.ofNullable(cloth);
    }

    public Optional<List<Cloth>> getAllCloths() {
        log.info("Método getCloth");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        return Optional.ofNullable(dynamoDBMapper.scan(Cloth.class, scanExpression));
    }

    public void deleteCloth(String cloth_id) {
        log.info("Method deleteCloth");
        Cloth cloth=null;
        Map<String, AttributeValue> eav= new HashMap<>();
        eav.put(":cloth_id", new AttributeValue().withS(cloth_id));

        DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
                .withFilterExpression("cloth_id = :cloth_id")
                .withExpressionAttributeValues(eav);

        List<Cloth> useResult=dynamoDBMapper.scan(Cloth.class, scanExpression);
        if(!useResult.isEmpty()) {
            cloth=useResult.get(0);
        }
        dynamoDBMapper.delete(cloth);
    }
}