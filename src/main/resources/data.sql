INSERT INTO users (id, email, first_name, last_name, image_url, is_active, password)
VALUES (1, "test@test.com", "FirstName", "LastName", NULL, TRUE, "123456");


INSERT INTO brands (id, name)
VALUES (1, 'Ford'),
       (2, 'Toyota');


INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, 'Fiesta', 'CAR', 1970, null, 1, 'https://cms.ford-edm.com/7bqmwvj5ngemhxrwe4kjtj26s342'),
       (2, 'Escort', 'CAR', 1980, null, 1, 'https://ford-cdn.fsn1.your-objectstorage.com/qd3boawyhofjbs34s4ag5stmb4a7'),
       (3, 'Yaris', 'CAR', 1990, null, 2, 'https://kong-proxy-intranet.toyota-europe.com/c1-images/resize/ccis/680x680/zip/bg/product-token/cf6e86f3-3ed3-466c-a1b4-d0d7bb7920cf/vehicle/ff9d5f02-92d1-46e5-85de-8941803a9e06/padding/50,50,50,50/image-quality/70/day-exterior-04_m19.png');