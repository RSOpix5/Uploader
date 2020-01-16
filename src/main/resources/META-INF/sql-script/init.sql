/*alter table public.images alter column albums type json using albums::json;
alter table public.images alter column albums set default '{"albums": "[]"}';
DELETE from public.images;
alter table public.images alter column s3_image_id type varchar(255) using s3_image_id::varchar(255);

INSERT INTO public.albums (id, author_id, name) VALUES (1, 1, 'A') ON CONFLICT DO NOTHING;
INSERT INTO public.albums (id, author_id, name) VALUES (2, 2, 'B') ON CONFLICT DO NOTHING;

INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (1, '{"albums": "[1,2]"}', 1, '2019-12-01 12:20:33.000000', '2019-12-01 12:20:37.000000', 'https://storage.googleapis.com/image-catalog-images/images/Kri%C5%A1tof.png') ON CONFLICT DO NOTHING;
INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (2, '{"albums": "[1]"}', 1, '2019-08-01 12:25:08.000000', '2019-08-01 12:25:08.000000', 'https://storage.googleapis.com/image-catalog-images/images/Kri%C5%A1tof_drugic.png') ON CONFLICT DO NOTHING;
INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (3, '{"albums": "[]"}', 1, '2019-12-01 12:20:33.000000', '2019-12-01 12:20:37.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;
INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (4, '{"albums": "[2]"}', 1, '2019-08-01 12:25:08.000000', '2019-08-01 12:25:08.000000', 'https://storage.googleapis.com/image-catalog-images/images/dwad.jpg') ON CONFLICT DO NOTHING;
INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (5, '{"albums": "[2]"}', 1, '2019-12-01 12:20:33.000000', '2019-12-01 12:20:37.000000', 'https://storage.googleapis.com/image-catalog-images/images/linusface.jpg') ON CONFLICT DO NOTHING;
INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (6, '{"albums": "[1]"}', 1, '2019-08-01 12:25:08.000000', '2019-08-01 12:25:08.000000', 'https://storage.googleapis.com/image-catalog-images/images/DISKSPACE.png') ON CONFLICT DO NOTHING;
*/
-- INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (7, '{"albums": "[1]"}', 2, '2019-12-01 12:20:33.000000', '2019-12-01 12:20:37.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;
-- INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (8, '{"albums": "[]"}', 2, '2019-08-01 12:25:08.000000', '2019-08-01 12:25:08.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;
-- INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (9, '{"albums": "[]"}', 2, '2019-12-01 12:20:33.000000', '2019-12-01 12:20:37.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;
-- INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (10, '{"albums": "[]"}', 2, '2019-08-01 12:25:08.000000', '2019-08-01 12:25:08.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;
-- INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (11, '{"albums": "[]"}', 1, '2019-12-01 12:20:33.000000', '2019-12-01 12:20:37.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;
-- INSERT INTO public.images (id, albums, author_id, created_date, modified_date, s3_image_id) VALUES (12, '{"albums": "[]"}', 1, '2019-08-01 12:25:08.000000', '2019-08-01 12:25:08.000000', 'https://storage.googleapis.com/image-catalog-images/images/MEDEVAC.png') ON CONFLICT DO NOTHING;