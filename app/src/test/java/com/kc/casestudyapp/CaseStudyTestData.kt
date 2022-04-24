package com.kc.casestudyapp

import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesDTO
import com.kc.casestudyapp.data.remote.dtos.casestudies.CaseStudiesItemDTO
import retrofit2.Response

object CaseStudyTestData {
    fun getCaseStudyResponse(): Response<CaseStudiesDTO> {
        return Response.success(CaseStudiesDTO(caseStudies = ArrayList<CaseStudiesItemDTO?>().apply {
            //empty data wich will not be consider due to filtration
            add(CaseStudiesItemDTO())
            add(getCaseStudiesItemDTO())
        }))
    }

    private fun getCaseStudiesItemDTO(): CaseStudiesItemDTO {
        return CaseStudiesItemDTO(
            heroImage = "https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_header-image-2x.jpg",
            teaser = "Testing Tube brakes, with TfL Decelerator"
        )
    }

    const val CASE_STUDIES_DATA = "{\n" +
            "    \"case_studies\": [\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"client\": \"TfL\",\n" +
            "        \"teaser\": \"Testing Tube brakes, with TfL Decelerator\",\n" +
            "        \"vertical\": \"Public Sector\",\n" +
            "        \"is_enterprise\": true,\n" +
            "        \"title\": \"A World-First For Apple iPad\",\n" +
            "        \"hero_image\": \"https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_header-image-2x.jpg\",\n" +
            "        \"sections\": [\n" +
            "        {\n" +
            "            \"title\": null,\n" +
            "            \"body_elements\": [\n" +
            "            \"With 1.34 billion passengers a year, the Tube is an intrinsic part of London life. Any disruptions, however small, to this essential service can cause a ripple effect that is felt not just on the network, but across the city itself.\",\n" +
            "            \"Examples of this can be seen in surprising places. Take brake testing. Each time a Tube train is suspected of faulty brakes, it is removed from service to be tested. This is because, in order to see if brakes are working as intended, the existing testing technology requires the train to be brought to a complete stop. The Underground’s tight schedule means if the train were left in service during this process, severe delays would occur across the line.\",\n" +
            "            \"However, what if the train’s brakes turn out to be working just fine? Removing a train from service has still proved disruptive to passengers, and incurred costs for TfL. In 2015, over a quarter of Tube trains removed for testing on just three Underground lines were actually found to be fault-free. Scale this up across all lines, and the impact to both engineering time and lost passenger hours becomes a sizeable problem.\",\n" +
            "            {\n" +
            "                \"image_url\": \"https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_tube-2x.jpg\"\n" +
            "            }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"Reimagining brake testing\",\n" +
            "            \"body_elements\": [\n" +
            "            \"Since 2014, Transport for London (TfL) and Kin + Carta Create have been working together to find leaner, more efficient ways of working, with the help of mobile technology. The inefficiencies within the existing brake testing process made it a prime candidate for the programme.\",\n" +
            "            \"The joint team began with a bold hypothesis: could the in-built sensors within an Apple iPad, which can measure speed, pitch, roll and yaw, match the accuracy of existing testing methods? And, most importantly, could it be used on a train in service - reducing the costly need to remove it?\",\n" +
            "            {\n" +
            "                \"image_url\": \"https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_pitch-roll-2x.jpg\"\n" +
            "            }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"Increasing speed-to-value\",\n" +
            "            \"body_elements\": [\n" +
            "            \"With such a bold hypothesis, it was important to first demonstrate the iPad could be a reliable replacement for existing testing technology. Over the course of just five days, Kin + Carta Create’ engineering team conducted a technical proof-of-concept that demonstrably proved the iPad was up to the task.\",\n" +
            "            \"The successful, quickfire experiment brought early value and strengthened the business case for a new, more efficient mode of brake testing. With the theory validated, the team then moved from proof-of-concept code to a production-ready app, now called Decelerator, in six short weeks.\",\n" +
            "            {\n" +
            "                \"image_url\": \"https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_graph-2x.jpg\"\n" +
            "            }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"Inspiring trust through design\",\n" +
            "            \"body_elements\": [\n" +
            "            \"The interface for Decelerator is about more than just good looks. The streamlined interface directs users efficiently through the app’s different stages: from set-up, to completing a brake test and sharing the results. Subtle, bespoke animations and interactions bring Decelerator to life, helping users to understand when to provide input, and how to interpret test results.\",\n" +
            "            {\n" +
            "                \"image_url\": \"https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_devices-2x.png\"\n" +
            "            },\n" +
            "            \"The design of Decelerator required the team to build a deep understanding of the conditions in which the app would be used - something that would have been impossible without close collaboration with TfL staff, and in-situ user testing. This process revealed a wealth of invaluable insight that shaped the design of Decelerator.\",\n" +
            "            \"An early iteration of the interface, for example, incorporated a bright visual theme. This was popular with users but, when tested in context underground, created too much glare. This potentially hazardous problem was resolved quickly, thanks to the app’s centrally-defined visual styles. A darker, less distracting visual theme was adopted in a matter of minutes, ready for immediate testing.\",\n" +
            "            {\n" +
            "                \"image_url\": \"https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_ipad-2x.jpg\"\n" +
            "            },\n" +
            "            \"Continuing TfL’s long history of global firsts and pioneering innovation, Train Decelerator is the first app of its kind - anywhere in the world. During 2017, it will be piloted across three underground lines and in Year One, aims to reduce the number of trains removed from service by a minimum of 75%. This will drive anticipated savings of £300,000 on these three lines alone - not to mention a dramatic reduction in Lost Customer Hours, a key metric for TfL.\",\n" +
            "            \"However, this is just the start of the potential savings Train Decelerator stands to make. Rolled out across the entire Underground network, and extended to other areas of transport, Train Decelerator has the capacity to save millions of pounds per year for TfL.\"\n" +
            "            ]\n" +
            "        }\n" +
            "        ]\n" +
            "    }\n" +
            "    ]\n" +
            "}"
}

