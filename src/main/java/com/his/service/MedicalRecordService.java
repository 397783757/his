package com.his.service;



import com.his.common.XRClient;
import com.his.model.MedicalRecord;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016-10-18.
 */
@Service
public class MedicalRecordService {
    //连接odoo数据库
    XmlRpcClient client = XRClient.getXmlRpcClient();
    public final String DB = XRClient.DB;
    public final int USERNAME = XRClient.USERNAME;
    public final String PASS = XRClient.PASS;

    List fields=Arrays.asList("id", "name", "doctor", "partner_id", "department", "doctor_licence",
            "d_time", "sex", "age", "street", "birthday", "phone", "note2", "note1",
            "diagnostic_type","times","readme","content","main_description",
            "nowill_description","historyill_description","personal_description",
            "familyill_history","doctor_advice","diagnose",
            "state","allergic_note","org_code");

    public Object getMedicalRecord() throws Exception {

        // 病历列表
        List<Object> prescriptions = Arrays.asList((Object[]) client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "sale.order", "search_read",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件
                                Arrays.asList("state", "=", "draft")
                        )),
                        new HashMap() {
                            {// 查询字段, 限定最多返回100条记录
                                put("fields",fields);
                                put("limit", 100);
                            }

                        })

        ));
        return prescriptions;
    }

    public String postMedicineRecord( final MedicalRecord medicalRecord){

        Integer id;
        try {
                id = (Integer) client.execute(
                    "execute_kw", Arrays.asList(

                            DB, USERNAME, PASS, "sale.order", "create",

                            Arrays.asList(new HashMap(){{
                                put("doctor",medicalRecord.getDoctor());put("id",medicalRecord.getId());
                                put("partner_id",medicalRecord.getPartner_id());
                                put("department",medicalRecord.getDepartment());put("doctor_licence",medicalRecord.getDoctor_licence());put("d_time",medicalRecord.getD_time());
                                put("sex",medicalRecord.getSex());put("age",medicalRecord.getAge());put("street",medicalRecord.getStreet());
                                put("birthday",medicalRecord.getBirthday());put("phone",medicalRecord.getPhone());put("note1",medicalRecord.getNote1());
                                put("note2",medicalRecord.getNote2());;put("diagnostic_type",medicalRecord.getDiagnostic_type());
                                put("prescription_type",medicalRecord.getPrescription_type());
                                put("times",medicalRecord.getTimes());
                                put("readme",medicalRecord.getReadme());put("content",medicalRecord.getContent());put("state","draft");
                                put("main_description",medicalRecord.getMain_description());put("nowill_description",medicalRecord.getNowill_description());
                                put("historyill_description",medicalRecord.getHistoryill_description());put("personal_description",medicalRecord.getPersonal_description());
                                put("familyill_history",medicalRecord.getFamilyill_history());put("doctor_advice",medicalRecord.getDoctor_advice());
                                put("diagnose",medicalRecord.getDiagnose());put("allergic_note",medicalRecord.getAllergic_note());
                                put("name",medicalRecord.getName());put("org_code",medicalRecord.getOrg_code());

                            }})));



        } catch (XmlRpcException e) {
            e.printStackTrace();
            System.out.println("添加失败");
            return null;
        }
        return id.toString();
    }

    public Object getMedicineRecord(Integer id) {

        List<Object> MedicineRecord=null;
        try {
            MedicineRecord=Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("id", "=", id)
                                    //Arrays.asList("state", "=", "sale")
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    put("limit", 10);
                                }

                            })

            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return MedicineRecord;
    }

    public String deleteMedicineRecord(final Integer id) {
        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "sale.order", "unlink",
                    Arrays.asList(Arrays.asList(id))));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String putMedicineRecord(final MedicalRecord medicalRecord) {

        try {
            client.execute("execute_kw", Arrays.asList(
                    DB, USERNAME, PASS,
                    "sale.order", "write",
                    Arrays.asList(
                            Arrays.asList(Integer.parseInt(medicalRecord.getId())),
                            new HashMap() {{
                                put("doctor",medicalRecord.getDoctor());put("partner_id",medicalRecord.getPartner_id());
                                put("department",medicalRecord.getDepartment());put("doctor_licence",medicalRecord.getDoctor_licence());
                                put("d_time",medicalRecord.getD_time());
                                put("sex",medicalRecord.getSex());put("age",medicalRecord.getAge());put("street",medicalRecord.getStreet());
                                put("birthday",medicalRecord.getBirthday());put("phone",medicalRecord.getPhone());put("note1",medicalRecord.getNote1());
                                put("note2",medicalRecord.getNote2());;put("diagnostic_type",medicalRecord.getDiagnostic_type());
                                put("times",medicalRecord.getTimes());put("readme",medicalRecord.getReadme());
                                put("content",medicalRecord.getContent());put("state","draft");
                                put("main_description",medicalRecord.getMain_description());put("nowill_description",medicalRecord.getNowill_description());
                                put("historyill_description",medicalRecord.getHistoryill_description());put("personal_description",medicalRecord.getPersonal_description());
                                put("familyill_history",medicalRecord.getFamilyill_history());put("doctor_advice",medicalRecord.getDoctor_advice());
                                put("diagnose",medicalRecord.getDiagnose());
                                put("allergic_note",medicalRecord.getAllergic_note());
                                put("prescription_type",medicalRecord.getPrescription_type());
                                put("org_code",medicalRecord.getOrg_code());


                            }})));

        } catch (XmlRpcException e) {
            e.printStackTrace();
            return "失败";
        }
        return "success";
    }

    public Object getThePage(Integer active){
        int len = 10;

        List<Object> medicines = null;
        try {
            medicines = Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("state", "=", "draft"))),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields",fields);
                                }
                            })));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        List<Object> medicine = new ArrayList();
       // int total=medicine.size();
        int i=active;
        int j ;
        if (medicines.size()!=0){

            int overal = medicines.size();
            if(i<=overal)
                for (j=len*(i-1);j<len*i&&j<overal;j++){

                    medicine.add(medicines.get(j));

                }
            Map map = new HashMap();

            map.put("data",medicine);
            map.put("active",overal);
            //map.put("total",total);
            return map;
        }

        return "无病历信息";

    }

    public Object getMedicalRecordByname(String partnerid,Integer page) throws Exception {

        final  int start=(page-1)*10;
        List<Object> prescriptions = Arrays.asList((Object[]) client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "sale.order", "search_read",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件
                                Arrays.asList("state", "=", "draft"),
                                Arrays.asList("partner_id", "like", partnerid)
                        )),
                        new HashMap() {
                            {// 查询字段, 限定最多返回100条记录
                                put("fields",fields);
                                { put("offset",start); put("limit", 10); }
                            }

                        })

        ));
        int total = (Integer)client.execute(
                "execute_kw", Arrays.asList(
                        DB, USERNAME, PASS, "sale.order", "search_count",
                        Arrays.asList(Arrays.asList(
                                // 设置查询条件

                                Arrays.asList("state", "=", "draft"),
                                Arrays.asList("partner_id", "like", partnerid)))));
        Map map = new HashMap();
        map.put("data",prescriptions);
        map.put("total",total);
        return map;
    }

    public Object getMedicineRecordByPatientId(Integer id) {

        List<Object> MedicineRecord=null;
        try {
            MedicineRecord=Arrays.asList((Object[]) client.execute(
                    "execute_kw", Arrays.asList(
                            DB, USERNAME, PASS, "sale.order", "search_read",
                            Arrays.asList(Arrays.asList(
                                    // 设置查询条件
                                    Arrays.asList("state", "=", "draft"),
                                    Arrays.asList("partner_id", "=", id)
                            )),
                            new HashMap() {
                                {// 查询字段, 限定最多返回100条记录
                                    put("fields", fields);
                                    put("limit", 10);
                                }

                            })

            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return MedicineRecord;
    }

}
