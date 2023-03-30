package com.smartform.backend.smartformbackend.template;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class TemplateService implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Template> templateList = mongoTemplate.findAll(Template.class);
        Map<String, Object> details = new HashMap<String, Object>();

        var template1 ="{\"_id\":{\"$oid\":\"64254fbc29c026709d5f1bdb\"},\"details\":{\"templateInfo\":{\"templateName\":\"NEWVENDORASSESSMENTFORM\",\"templateDesc\":\"NEWVENDORASSESSMENTFORM\"},\"templateContents\":[{\"vendor\":[{\"type\":\"header\",\"order\":0,\"text\":\"NewVendorAssessmentForm\",\"style\":\"h1\",\"required\":true},{\"type\":\"text\",\"order\":1,\"text\":\"Company'sName\",\"required\":true},{\"type\":\"number\",\"order\":2,\"text\":\"CompanyRegistrationNo:\",\"required\":true},{\"type\":\"text\",\"order\":3,\"text\":\"OfficeAddress\",\"required\":true},{\"type\":\"boolean\",\"order\":4,\"text\":\"GSTRegistered\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"number\",\"order\":5,\"text\":\"Tel\",\"required\":true},{\"type\":\"text\",\"order\":6,\"text\":\"Fax\",\"required\":true},{\"type\":\"checkbox\",\"order\":7,\"text\":\"TypeofbusinessLicense/Registration\",\"options\":[\"SoleProprietorship\",\"LimitedCompany\",\"PartnershipAgreement\",\"Others\"],\"required\":true,\"shortAnswer\":true},{\"type\":\"header\",\"order\":0,\"text\":\"ContactPerson\",\"style\":\"h1\",\"required\":true},{\"type\":\"text\",\"order\":8,\"text\":\"ContactName\",\"required\":true},{\"type\":\"number\",\"order\":9,\"text\":\"ContactTel\",\"required\":true},{\"type\":\"text\",\"order\":10,\"text\":\"ContactDesignation\",\"required\":true},{\"type\":\"checkbox\",\"order\":11,\"text\":\"NatureofBusiness\",\"options\":[\"Manufacturing\",\"Agent/dealer\",\"Distributor\",\"Others\"],\"required\":true,\"shortAnswer\":true},{\"type\":\"text\",\"order\":12,\"text\":\"Product/Services\",\"required\":true}]},{\"admin\":[{\"type\":\"header\",\"order\":0,\"text\":\"NewVendorAssessmentForm\",\"style\":\"h1\",\"required\":true},{\"type\":\"checkbox\",\"order\":1,\"text\":\"Evaluation\",\"options\":[\"ISO9001Certification:\",\"AccreditationofLaboratory:\",\"ProductCertification:\",\"SiteEvaluationResults:\",\"ResultsofSamples/ProductEvaluation:\",\"ResultsofFirstDeal:\",\"TrackRecordReview/CustomerReference:\",\"Others(e.g.commercial,solesupplier,customerspecified,franchiseetc.)\"],\"required\":true,\"shortAnswer\":true},{\"type\":\"radio\",\"order\":2,\"text\":\"ResultofEvaluation\",\"options\":[\"Approved\",\"NotApproved\"],\"required\":true},{\"type\":\"text\",\"order\":3,\"text\":\"EvaluatedBy\",\"required\":true},{\"type\":\"text\",\"order\":4,\"text\":\"ApprovedByDirector\",\"required\":true},{\"type\":\"date\",\"order\":5,\"text\":\"EffectiveDate\",\"required\":true}]}]},\"_class\":\"com.smartform.backend.smartformbackend.template.Template\"}";
        var template2 = "{\"_id\":{\"$oid\":\"642548e8483a524a34d9867e\"},\"details\":{\"templateInfo\":{\"templateName\":\"SUBCONTRACTOR’SSAFETY&HEALTHPRE-EVALUATION\",\"templateDesc\":\"SUBCONTRACTOR’SSAFETY&HEALTHPRE-EVALUATION\"},\"templateContents\":[{\"vendor\":[{\"type\":\"text\",\"order\":0,\"text\":\"NameofSub-Contractor\",\"required\":true},{\"type\":\"text\",\"order\":1,\"text\":\"ScopeofWork\",\"required\":true},{\"type\":\"text\",\"order\":2,\"text\":\"EvaluatedBy\",\"required\":true},{\"type\":\"date\",\"order\":3,\"text\":\"Date\",\"required\":true},{\"type\":\"header\",\"order\":4,\"text\":\"Safety&HealthPolicyandOrganisation\",\"style\":\"h3\",\"required\":true},{\"type\":\"boolean\",\"order\":5,\"text\":\"IsthereawrittenSafety&HealthPolicy?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":6,\"text\":\"IsthereaSafetyOrganisationwithproperdelegationofresponsibilityandaccountabilityforsafetyandhealth?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":7,\"text\":\"Isthereawrittensafetycommitmentandisitsubmitted?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"header\",\"order\":8,\"text\":\"ToolBoxMeeting\",\"style\":\"h3\",\"required\":true},{\"type\":\"boolean\",\"order\":9,\"text\":\"Areregulartool-boxmeetingsconductedandreportssubmitted?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"header\",\"order\":10,\"text\":\"SafetyTraining\",\"style\":\"h3\",\"required\":true},{\"type\":\"boolean\",\"order\":11,\"text\":\"Arerelevantsafetytrainingcoursesprovidedformanagement/Supervisors?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":12,\"text\":\"Arerelevantsafetytrainingcoursesprovidedforworkers?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":13,\"text\":\"Arerelevantsafetytrainingcertificatessubmitted?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"header\",\"order\":14,\"text\":\"Safety&HealthRules&SafeWorkProcedures/RiskAssessment\",\"style\":\"h3\",\"required\":true},{\"type\":\"boolean\",\"order\":15,\"text\":\"Aretherewrittensafety&healthrulesfortheworkers?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":16,\"text\":\"Aretherewrittensafeworkprocedures/riskassessmentformulatedandsubmitted?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"header\",\"order\":17,\"text\":\"Safety&HealthInspection&Equipment\",\"style\":\"h3\",\"required\":true},{\"type\":\"boolean\",\"order\":18,\"text\":\"Isthereawrittenprogrammeoutlininginspectionguidelines,frequencyandfollow-upcorrectiveactions?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":19,\"text\":\"IsthereavailablePersonalProtectiveEquipmentandofproperworkingconditionandcomplytosafetyrequirements?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"header\",\"order\":20,\"text\":\"ResponsiblePersonnel\",\"style\":\"h3\",\"required\":true},{\"type\":\"boolean\",\"order\":21,\"text\":\"IsthereaSafetySupervisorworkingatleast5hrs/week?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":22,\"text\":\"IsthereaqualifiedFirstAider?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"boolean\",\"order\":23,\"text\":\"ArethererelevantLicensedElectricalWorkers,qualifiedengineers,qualifiedsupervisors,liftingsupervisors,qualifiedJCBTower/Mobile/CrawlerCraneoperators?\",\"options\":[\"Yes\",\"No\"],\"required\":true},{\"type\":\"header\",\"order\":24,\"text\":\"AccidentAnalysis\",\"style\":\"h1\",\"required\":true},{\"type\":\"number\",\"order\":25,\"text\":\"AccidentRecordsforthelastthreeyears:NumberofTemporaryDisabilityCases\",\"required\":true},{\"type\":\"number\",\"order\":26,\"text\":\"AccidentRecordsforthelastthreeyears:NumberofPermanentDisabilityCases\",\"required\":true},{\"type\":\"number\",\"order\":27,\"text\":\"AccidentRecordsforthelastthreeyears:NumberofFatalCases\",\"required\":true},{\"type\":\"acknowledgement\",\"required\":true}]}]},\"_class\":\"com.smartform.backend.smartformbackend.template.Template\"}";
        var template3 = "{\"_id\":{\"$oid\":\"64254c53483a524a34d9867f\"},\"details\":{\"templateInfo\":{\"templateName\":\"SUBCONTRACTOR’SSAFETY&HEALTHPERFORMANCEEVALUATION\",\"templateDesc\":\"SUBCONTRACTOR’SSAFETY&HEALTHPERFORMANCEEVALUATION\"},\"templateContents\":[{\"vendor\":[{\"type\":\"text\",\"order\":0,\"text\":\"NameofContractor:\",\"required\":true},{\"type\":\"text\",\"order\":1,\"text\":\"Trade:\",\"required\":true},{\"type\":\"text\",\"order\":2,\"text\":\"Project/Worksite:\",\"required\":true},{\"type\":\"date\",\"order\":3,\"text\":\"DateofEvaluation:\",\"required\":true},{\"type\":\"likertGroup\",\"order\":5,\"text\":\"Part1:Participationinsafety\",\"options\":[\"1.AttendanceinSafetyMeeting\",\"2.ToolBoxMeeting\",\"3.ComplianceToRules&Regulation\",\"4.SafetyPromotionalActivities\",\"5.DocumentSubmission\"],\"required\":true},{\"type\":\"likertGroup\",\"order\":5,\"text\":\"PARTII:SAFETYTRAININGANDCOMPETENCIES(PERCENTAGEOFATTENDANCE)\",\"options\":[\"6.StatutorySafetyTrainingCourse\",\"7.SafetyTradeCourse\",\"8.MassSafetyTalk\",\"9.WSHSafetyCoordinator/Supervisor\",\"10.OtherSafetyTraining\"],\"required\":true},{\"type\":\"likertGroup\",\"order\":6,\"text\":\"PARTIII:ACCIDENT/INCIDENTPREVIEW\",\"options\":[\"11.EffortinAccidentPrevention\",\"12.SafeWorkPractice/PermitToWork\",\"13.IncidentSeverity&FrequencyRate\",\"14.SafetyOffence\",\"15.SafetyInspectionAndRectification\"],\"required\":true},{\"type\":\"likertGroup\",\"order\":7,\"text\":\"PARTIV:MAINTENANCEOFEQUIPMENT\",\"options\":[\"16.ExplosivePoweredTool/CuttingTool\",\"17.Ladder\",\"18.LiftingGear/Appliance/Machine\",\"19.ElectricalEquipment/Compressor\",\"20.OtherMachineries\"],\"required\":true},{\"type\":\"likertGroup\",\"order\":8,\"text\":\"PARTV:GENERALHOUSEKEEPING&Others\",\"options\":[\"21.SubconSnrMgtCommitmenttoHS\",\"22.CompliancewithPPE\",\"23.Housekeeping&CleanlinessatSite\",\"24.Housekeeping&CleanlinessatStore\",\"25.Housekeeping&CleanlinessatQuarter\"],\"required\":true},{\"type\":\"text\",\"order\":9,\"text\":\"Comments\",\"required\":true},{\"type\":\"header\",\"order\":10,\"text\":\"EvaluationDoneBy:\",\"style\":\"h1\",\"required\":true},{\"type\":\"text\",\"order\":11,\"text\":\"NameofSafetyCoordinator:\",\"required\":true},{\"type\":\"date\",\"order\":12,\"text\":\"Date:\",\"required\":true}]},{\"admin\":[{\"type\":\"header\",\"order\":0,\"text\":\"ApprovedBy:\",\"style\":\"h1\",\"required\":true},{\"type\":\"text\",\"order\":1,\"text\":\"NameofDirector:\",\"required\":true},{\"type\":\"date\",\"order\":2,\"text\":\"Date:\",\"required\":true}]}]},\"_class\":\"com.smartform.backend.smartformbackend.template.Template\"}";
        Document template1Doc = Document.parse(template1);
        Document template2Doc = Document.parse(template2);
        Document template3Doc = Document.parse(template3);

        
        var vendorJson = "{\"details\":{\"templateInfo\":{\"templateName\":\"NewVendorAssessmentForm\",\"assignedTo\":\"Vendor\",\"templateDesc\":\"Assessmentfornewvendors\"},\"templateContents\":[{\"vendor\":[{\"type\":\"header\",\"order\":0,\"text\":\"NEWVENDORASSESSMENTFORM\",\"style\":\"h1\"},{\"type\":\"text\",\"order\":1,\"text\":\"Company'sName\"},{\"type\":\"number\",\"order\":2,\"text\":\"CompanyRegistrationNo:\"},{\"type\":\"text\",\"order\":3,\"text\":\"OfficeAddress\"},{\"type\":\"boolean\",\"order\":4,\"text\":\"GSTRegistered\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"number\",\"order\":5,\"text\":\"Tel\"},{\"type\":\"text\",\"order\":6,\"text\":\"Fax\"},{\"type\":\"checkbox\",\"order\":7,\"text\":\"TypeofbusinessLicense/Registration\",\"options\":[\"SoleProprietorship\",\"LimitedCompany\",\"PartnershipAgreement\",\"Others\"]},{\"type\":\"header\",\"order\":0,\"text\":\"ContactPerson\",\"style\":\"h1\"},{\"type\":\"text\",\"order\":8,\"text\":\"ContactName\"},{\"type\":\"number\",\"order\":9,\"text\":\"ContactTel\"},{\"type\":\"text\",\"order\":10,\"text\":\"ContactDesignation\"},{\"type\":\"checkbox\",\"order\":11,\"text\":\"NatureofBusiness\",\"options\":[\"Manufacturing\",\"Agent/dealer\",\"Distributor\",\"Others\"]},{\"type\":\"text\",\"order\":12,\"text\":\"IfyoupickedOtherspleasespecifythenatureofyourbusiness\"}]},{\"admin\":[{\"type\":\"header\",\"order\":0,\"text\":\"NEWVENDORASSESSMENTFORM\",\"style\":\"h1\"},{\"type\":\"boolean\",\"order\":2,\"text\":\"ISO9001Certification\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":3,\"text\":\"CertificationBody\"},{\"type\":\"boolean\",\"order\":4,\"text\":\"AccreditationofLaboratory\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":5,\"text\":\"AccreditationBody\"},{\"type\":\"boolean\",\"order\":6,\"text\":\"ProductCertification\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":7,\"text\":\"ProductMarkings(e.g.PSB,UL,TUV)\"},{\"type\":\"boolean\",\"order\":8,\"text\":\"SiteEvaluationResults\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":9,\"text\":\"SiteEvaluationResults\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":10,\"text\":\"ResultsofSamples/ProductEvaluation\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":11,\"text\":\"ResultsofSamples/ProductEvaluation\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":12,\"text\":\"ResultsofFirstDeal\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":13,\"text\":\"ResultsofFirstDeal\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":14,\"text\":\"TrackRecordReview/CustomerReference\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"checkbox\",\"order\":15,\"text\":\"TrackRecordReview/CustomerReference\",\"options\":[\"Satisfactory\",\"Unsatisfactory\"]},{\"type\":\"boolean\",\"order\":16,\"text\":\"OtherEvaluation\",\"options\":[\"Yes\",\"No\"]},{\"type\":\"text\",\"order\":17,\"text\":\"Specify:ProductMarkings(e.g.PSB,UL,TUV)\"}]}]}}";

        JSONObject jObject = new JSONObject(vendorJson);
        Document doc = Document.parse(vendorJson);

        
        mongoTemplate.insert(doc, "templates");

    }
}
