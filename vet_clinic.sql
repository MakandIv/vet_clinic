PGDMP     5    /                z         
   vet_clinic    14.4    14.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16632 
   vet_clinic    DATABASE     g   CREATE DATABASE vet_clinic WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE vet_clinic;
                postgres    false            �            1259    16663    doctors    TABLE     �   CREATE TABLE public.doctors (
    "ID_doctor" integer NOT NULL,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    16668    doctors_ID_doctor_seq    SEQUENCE     �   ALTER TABLE public.doctors ALTER COLUMN "ID_doctor" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."doctors_ID_doctor_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1
);
            public          postgres    false    209            �            1259    16669    patients    TABLE     �   CREATE TABLE public.patients (
    "ID_patient" integer NOT NULL,
    registration_timestamp timestamp with time zone NOT NULL,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text
);
    DROP TABLE public.patients;
       public         heap    postgres    false            �            1259    16674    patients_ID_patient_seq    SEQUENCE     �   ALTER TABLE public.patients ALTER COLUMN "ID_patient" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."patients_ID_patient_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1
);
            public          postgres    false    211            �            1259    16675 
   receptions    TABLE     �   CREATE TABLE public.receptions (
    "ID_reception" integer NOT NULL,
    "ID_patient" integer NOT NULL,
    status text NOT NULL,
    "ID_doctor" integer NOT NULL
);
    DROP TABLE public.receptions;
       public         heap    postgres    false            �           0    0     COLUMN receptions."ID_reception"    COMMENT     O   COMMENT ON COLUMN public.receptions."ID_reception" IS 'Identifiers reception';
          public          postgres    false    213            �            1259    16680    receptions_ID_reception_seq    SEQUENCE     �   ALTER TABLE public.receptions ALTER COLUMN "ID_reception" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."receptions_ID_reception_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1
);
            public          postgres    false    213            g           2606    16682    doctors doctors_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY ("ID_doctor");
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    209            i           2606    16684    patients patients_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY ("ID_patient");
 @   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_pkey;
       public            postgres    false    211            l           2606    16686    receptions receptions_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.receptions
    ADD CONSTRAINT receptions_pkey PRIMARY KEY ("ID_reception");
 D   ALTER TABLE ONLY public.receptions DROP CONSTRAINT receptions_pkey;
       public            postgres    false    213            j           1259    16687    fki_reception_patient    INDEX     T   CREATE INDEX fki_reception_patient ON public.receptions USING btree ("ID_patient");
 )   DROP INDEX public.fki_reception_patient;
       public            postgres    false    213            m           2606    16688    receptions reception_patient    FK CONSTRAINT     �   ALTER TABLE ONLY public.receptions
    ADD CONSTRAINT reception_patient FOREIGN KEY ("ID_patient") REFERENCES public.patients("ID_patient") ON UPDATE CASCADE ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.receptions DROP CONSTRAINT reception_patient;
       public          postgres    false    213    3177    211           