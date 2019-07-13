package com.pehand.app.backend.subservice_details;

import com.pehand.app.pojos.SubServiceDetails;

public class SubServiceDetailsRepositoryImplTemp implements SubServiceDetailsRepository {
    @Override
    public SubServiceDetails getSubServiceDetailsById(int id) {
        SubServiceDetails s1 = new SubServiceDetails(11,"تركيب تكييف 3 حصان", "300",
                "https://www.pehand.com/files/SubService/11/air-conditioner-installation.jpg"
                ,"\"- تركيب جميع انواع التكييفات شارب وكارير ويونيون اير وباور وجرى والتكييف الصحراوى وباقى انواع التكييف\\r\\n</br>\\r\\n</br>- ملحوظة:\\r\\n</br>- التركيب غير شاملة تكلفة الحامل\\r\\n</br>- الاسعار غير شاملة تكسير لتركيب تكييف ال window\\r\\n</br>- السعر غير شامل قطع غيار\\r\\n</br>- السعر لوحدة تكييف واحدة فقط",
                1, "جنيه");
        SubServiceDetails s2 = new SubServiceDetails(9,"تركيب تكييف 1.5حصان", "250",
                "https://www.pehand.com/files/SubService/9/air-conditioner-installation.jpg"
                ,"- تركيب جميع انواع التكييفات شارب وكارير ويونيون اير وباور وجرى والتكييف الصحراوى وباقى انواع التكييف\\r\\n</br>\\r\\n</br>- ملحوظة:\\r\\n</br>- التركيب غير شاملة تكلفة الحامل\\r\\n</br>- الاسعار غير شاملة تكسير لتركيب تكييف ال window\\r\\n</br>- السعر غير شامل قطع غيار\\r\\n</br>- السعر لوحدة تكييف واحدة فقط",
                1, "جنيه");
        SubServiceDetails s3 = new SubServiceDetails(22,"أعمال الكهرباء", "25",
                "https://www.pehand.com/files/SubService/22/electromechanical.png"
                ,"- تركيب جميع انواع التكييفات شارب وكارير ويونيون اير وباور وجرى والتكييف الصحراوى وباقى انواع التكييف\\r\\n</br>\\r\\n</br>- ملحوظة:\\r\\n</br>- التركيب غير شاملة تكلفة الحامل\\r\\n</br>- الاسعار غير شاملة تكسير لتركيب تكييف ال window\\r\\n</br>- السعر غير شامل قطع غيار\\r\\n</br>- السعر لوحدة تكييف واحدة فقط",
                3, "جنيه للمعاينة ");
        SubServiceDetails s4 = new SubServiceDetails(
                30,"تأسيس سباكة 2 حمام + 1 مطبخ", "1200",
                "https://www.pehand.com/files/SubService/30/1.jpg"
                ,"- تأسيس 2 حمام + 1 مطبخ  (صرف وتغذية)ويشمل الآتى:\\r\\n</br>\\r\\n</br>- حمام كبير:\\r\\n</br>- تأسيس حوض الوجه     \\r\\n</br>- تأسيس خلاط دش \\r\\n</br>- تأسيس كومبينيشن \\r\\n</br>- صرف حوض الوجه     \\r\\n</br>- بلاعة الصرف (البيبة )\\r\\n</br>\\r\\n</br>- حمام صغير:\\r\\n</br>- تأسيس حوض الوجه     \\r\\n</br>- تأسيس كومبينيشن \\r\\n</br>- صرف حوض الوجه     \\r\\n</br>- بلاعة الصرف (البيبة )\\r\\n</br>\\r\\n</br>- مطبخ :\\r\\n</br>- تأسيس حوض مطبخ \\r\\n</br>- تأسيس فلتر   \\r\\n</br>- صرف حوض مطبخ \\r\\n</br>\\r\\n</br>- العرض لا يشمل:\\r\\n</br>- الخامات\\r\\n</br>- عملية التأسيس تتم من داخل الشقة فقط بدون استخدام حبل من خارج الشقة\\r\\n</br>- بدون عزل ارضية\\r\\n</br>- بدون تشطيب\\r\\n</br>\\r\\n</br>-ملاحظة:\\r\\n</br>ان يكون الحمام او المطبخ على الطوب\\r\\n</br>- فى حالات المعاينة فقط تكون مصروفات المعاينة 25 جنيه فى جميع المناطق",
                8, "جنيه "
        );
        switch (id) {
            case 11 : return s1;
            case 9 : return s2;
            case 22 : return s3;
            case 30 : return s4;
            default: return s1;
        }
    }
}
