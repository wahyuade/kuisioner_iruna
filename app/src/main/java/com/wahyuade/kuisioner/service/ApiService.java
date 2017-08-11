package com.wahyuade.kuisioner.service;

import com.wahyuade.kuisioner.model.DefaultModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wahyuade on 10/08/17.
 */

public class ApiService {
        public static String BASE_URL = "http://iruna.wahyuade.com";

        public static PostService service_post = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.PostService.class);

        public interface PostService{
                //pembeli
                @FormUrlEncoded
                @POST("/pembeli/biodata")
                Call<DefaultModel> postBiodataPembeli(@Field("nama") String nama, @Field("alamat") String alamat, @Field("no_telp") String no_telp, @Field("email") String email);

                @FormUrlEncoded
                @POST("/pembeli/kebutuhan-tertutup")
                Call<DefaultModel> postKebutuhanTertutupPembeli(@Field("email") String email, @Field("sem") String sem, @Field("mak_min") String mak_min, @Field("alt_rmh_tg") String alt_rmh_tg, @Field("el_aks") String el_aks, @Field("oto") String oto, @Field("bhn_kim") String bhn_kim);

                @FormUrlEncoded
                @POST("/pembeli/kebutuhan-terbuka")
                Call<DefaultModel> postKebutuhanTerbukaPembeli(@Field("email") String email, @Field("nama") String nama);

                @FormUrlEncoded
                @POST("/pembeli/referensi")
                Call<DefaultModel> postReferensiUKM(@Field("email")String email, @Field("nama_ukm")String nama_ukm, @Field("nama_penjual") String nama_penjual, @Field("no_telp") String no_telp, @Field("alamat") String alamat, @Field("produk") String produk);

                @FormUrlEncoded
                @POST("/pembeli/hari")
                Call<DefaultModel> postHariTransaksiPembeli(@Field("email") String email, @Field("senin") String senin, @Field("selasa") String selasa, @Field("rabu") String rabu, @Field("kamis") String kamis, @Field("jumat") String jumat, @Field("sabtu") String sabtu, @Field("minggu") String minggu);

                @FormUrlEncoded
                @POST("/pembeli/waktu_buka")
                Call<DefaultModel> postWaktuBukaPembeli(@Field("email") String email, @Field("jam") String jam, @Field("menit") String menit);

                @FormUrlEncoded
                @POST("/pembeli/waktu_tutup")
                Call<DefaultModel> postWaktuTutupPembeli(@Field("email") String email, @Field("jam") String jam, @Field("menit") String menit);

                @FormUrlEncoded
                @POST("/pembeli/pembayaran_tertutup")
                Call<DefaultModel> postPembayaranTertutupPembeli(@Field("email") String email, @Field("id_pembayaran") String id_pembayaran);

                @FormUrlEncoded
                @POST("/pembeli/pembayaran_terbuka")
                Call<DefaultModel> postPembayaranTerbukaPembeli(@Field("email") String email, @Field("nama") String nama);

                @FormUrlEncoded
                @POST("/pembeli/fee_agreement")
                Call<DefaultModel> postFeeAgreementPembeli(@Field("email") String email, @Field("agreement") String agreement);


                //penjual
                @FormUrlEncoded
                @POST("/penjual/biodata")
                Call<DefaultModel> postBiodataPenjual(@Field("nama_ukm") String nama_ukm, @Field("nama") String nama, @Field("alamat") String alamat, @Field("no_telp") String no_telp, @Field("email") String email);

                @FormUrlEncoded
                @POST("/penjual/kebutuhan-tertutup")
                Call<DefaultModel> postKebutuhanTertutupPenjual(@Field("email") String email, @Field("sem") String sem, @Field("mak_min") String mak_min, @Field("alt_rmh_tg") String alt_rmh_tg, @Field("el_aks") String el_aks, @Field("oto") String oto, @Field("bhn_kim") String bhn_kim);

                @FormUrlEncoded
                @POST("/penjual/kebutuhan-terbuka")
                Call<DefaultModel> postKebutuhanTerbukaPenjual(@Field("email") String email, @Field("nama") String nama);

                @FormUrlEncoded
                @POST("/penjual/hari")
                Call<DefaultModel> postHariTransaksiPenjual(@Field("email") String email, @Field("senin") String senin, @Field("selasa") String selasa, @Field("rabu") String rabu, @Field("kamis") String kamis, @Field("jumat") String jumat, @Field("sabtu") String sabtu, @Field("minggu") String minggu);

                @FormUrlEncoded
                @POST("/penjual/waktu_buka")
                Call<DefaultModel> postWaktuBukaPenjual(@Field("email") String email, @Field("jam") String jam, @Field("menit") String menit);

                @FormUrlEncoded
                @POST("/penjual/waktu_tutup")
                Call<DefaultModel> postWaktuTutupPenjual(@Field("email") String email, @Field("jam") String jam, @Field("menit") String menit);

                @FormUrlEncoded
                @POST("/penjual/pembayaran_tertutup")
                Call<DefaultModel> postPembayaranTertutupPenjual(@Field("email") String email, @Field("id_pembayaran") String id_pembayaran);

                @FormUrlEncoded
                @POST("/penjual/pembayaran_terbuka")
                Call<DefaultModel> postPembayaranTerbukaPenjual(@Field("email") String email, @Field("nama") String nama);

                @FormUrlEncoded
                @POST("/penjual/fee_agreement")
                Call<DefaultModel> postFeeAgreementPenjual(@Field("email") String email, @Field("agreement") String agreement);

                @FormUrlEncoded
                @POST("/penjual/pilihan_harga")
                Call<DefaultModel> postPilihanHarga(@Field("email") String email, @Field("pilihan") String pilihan);
        }
}
