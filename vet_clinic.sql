PGDMP     7                    z         
   vet_clinic    14.4    14.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    16394 
   vet_clinic    DATABASE     g   CREATE DATABASE vet_clinic WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE vet_clinic;
                postgres    false            �            1259    16407    appointments    TABLE     �   CREATE TABLE public.appointments (
    id_appointment integer NOT NULL,
    id_patient integer NOT NULL,
    status text NOT NULL,
    id_doctor integer NOT NULL,
    appointment_timestamp timestamp without time zone NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            
           0    0 "   COLUMN appointments.id_appointment    COMMENT     Q   COMMENT ON COLUMN public.appointments.id_appointment IS 'Identifiers reception';
          public          postgres    false    213            �            1259    16395    doctors    TABLE     �   CREATE TABLE public.doctors (
    id_doctor integer NOT NULL,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    16400    doctors_ID_doctor_seq    SEQUENCE     �   ALTER TABLE public.doctors ALTER COLUMN id_doctor ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."doctors_ID_doctor_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1
);
            public          postgres    false    209            �            1259    16401    patients    TABLE     �   CREATE TABLE public.patients (
    id_patient integer NOT NULL,
    registration_timestamp timestamp without time zone NOT NULL,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text
);
    DROP TABLE public.patients;
       public         heap    postgres    false            �            1259    16406    patients_ID_patient_seq    SEQUENCE     �   ALTER TABLE public.patients ALTER COLUMN id_patient ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."patients_ID_patient_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1
);
            public          postgres    false    211            �            1259    16412    receptions_ID_reception_seq    SEQUENCE     �   ALTER TABLE public.appointments ALTER COLUMN id_appointment ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."receptions_ID_reception_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1
);
            public          postgres    false    213            �            1259    16438    view_appointments    VIEW     �   CREATE VIEW public.view_appointments AS
 SELECT patients.id_patient,
    patients.registration_timestamp,
    patients.surname,
    patients.name,
    patients.patronymic
   FROM public.patients;
 $   DROP VIEW public.view_appointments;
       public          postgres    false    211    211    211    211    211                      0    16407    appointments 
   TABLE DATA                 public          postgres    false    213   5       �          0    16395    doctors 
   TABLE DATA                 public          postgres    false    209   �                  0    16401    patients 
   TABLE DATA                 public          postgres    false    211   �                  0    0    doctors_ID_doctor_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public."doctors_ID_doctor_seq"', 4, true);
          public          postgres    false    210                       0    0    patients_ID_patient_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public."patients_ID_patient_seq"', 1, true);
          public          postgres    false    212                       0    0    receptions_ID_reception_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."receptions_ID_reception_seq"', 2, true);
          public          postgres    false    214            k           2606    16414    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id_doctor);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    209            m           2606    16416    patients patients_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (id_patient);
 @   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_pkey;
       public            postgres    false    211            p           2606    16418    appointments receptions_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT receptions_pkey PRIMARY KEY (id_appointment);
 F   ALTER TABLE ONLY public.appointments DROP CONSTRAINT receptions_pkey;
       public            postgres    false    213            n           1259    16419    fki_reception_patient    INDEX     T   CREATE INDEX fki_reception_patient ON public.appointments USING btree (id_patient);
 )   DROP INDEX public.fki_reception_patient;
       public            postgres    false    213            q           2606    16420    appointments reception_patient    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT reception_patient FOREIGN KEY (id_patient) REFERENCES public.patients(id_patient) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT reception_patient;
       public          postgres    false    3181    213    211               �   x���v
Q���W((M��L�K,(���+�M�+)V��L�G�Q �K2���Ē�b�XJ~rI~��������T���M�0נ OO?w����W_�0G�PW��a�� D�y���:
�@����������������������5���p��@��� �my      �   �   x���v
Q���W((M��L�K�O.�/*V��L���u�K��sSu dAbIQ~^enf����kP������Bpdp���B��O�+�V�0�QP�0�¾�/츰HoR�̿��¦[/��^l�� ����b���5�'�]f�y!�U[.��v�T�K�|0{�t'�]\\ b�~8          �   x�-��
�@E�~�۩`��Y�*Hb�Z�dC=pFqƠ�ϲ��Υ9��
h^0L�[��2�7�`�f��W�A)��r�@O��Rx�p���zKl](�,��G`WVeg���K���z`���U�Ha��m��:��Mlϵ�;��f�+��	�ۧ��,�� ��;�     