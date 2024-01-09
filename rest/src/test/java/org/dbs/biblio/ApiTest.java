package org.dbs.biblio;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
class ApiTest {

    @Test
    void testDataProvider() {
        RestAssured.baseURI = "http://aragorn:8090";

        RequestSpecification request = given()
                .header("accept", "application/json");


        Response response = request.when()
                .get("/api/dataProvider/provide");

        List<SourceDescription> sourceDescriptions = response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath()
                .getList(".", SourceDescription.class);
        Map<String, SourceDescription> sourceDescriptionsByName = sourceDescriptions.stream()
                        .collect(Collectors.toMap(SourceDescription::getDataName, Function.identity()));
        assertThat(sourceDescriptionsByName).hasSize(5);

        assertThat(sourceDescriptionsByName.get("IsbnStock").getSizeOf()).isEqualTo(1000);
        assertThat(sourceDescriptionsByName.get("Adresse").getSizeOf()).isZero();
        assertThat(sourceDescriptionsByName.get("City").getSizeOf()).isZero();
        assertThat(sourceDescriptionsByName.get("FirstName").getSizeOf()).isEqualTo(209309);
        assertThat(sourceDescriptionsByName.get("LastName").getSizeOf()).isEqualTo(879421);
    }
}