import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:spyglass_ui_flutter/components/custom_text_field.dart';
import 'package:spyglass_ui_flutter/components/floating_app_bar.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';

class ContactUs extends StatelessWidget {

  final Map<String, TextEditingController> controllers = {
    'name' : new TextEditingController(),
    'email': new TextEditingController(),
    'phone': new TextEditingController(),
    'organization': new TextEditingController(),
    'message' : new TextEditingController(),
  };

  final Map<String, String> labels = {
    'name' : "Name",
    'email': "Email",
    'phone': "Phone Number",
    'message' : "Message",
  };

    final Map<String, String> hintTexts = {
    'name' : "Enter your name",
    'email': "Enter your email",
    'phone': "Enter your phone number",
    'message' : "Enter your message here....",
  };

  final double containerHeights = 400.0;

  Widget contactDetailsFragment(IconData icon, String text) {
    return Row(
      children: [
        SizedBox(width: 40,),
        Icon(icon),
        SizedBox(width: 20,),
        SelectableText(text)
      ],
    );
  }

  Widget buildForm(String section) {
    return section == 'message' ? 
       ThemedTextField(
        controller: controllers[section]!,
        label: labels[section]!,
        hintText: hintTexts[section]!,
        isMultiLine: true,
        numOfLines: 4,
        isPassword: false,
      ) : ThemedTextField(
        controller: controllers[section]!,
        label: labels[section]!,
        hintText: hintTexts[section]!,
        isPassword: false,
        isMultiLine: false,
        numOfLines: 1,
      );
  }


  Widget build(BuildContext context) {
    return Padding(padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
    child: Scaffold(
      appBar: FloatingAppBar(context: context,),
      body: Padding(
        padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
        child: Container(
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Text(
                "Contact Us",
                style: GoogleFonts.alexBrush(
                  textStyle: TextStyle(
                  fontSize: 48,
                ),
                )
              ),
              SizedBox(height: 10,),
              Text(
                "We love questions and feedback - and we're always happy to help!",
                style: TextStyle(color: Colors.grey[700]),),
                            SizedBox(height: 10,),
        
              Text("Here are some ways to contact us.",
              style: TextStyle(color: Colors.grey[700]),),
              SizedBox(height: 40,),
                ],
              ),
        
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                Container(
                  width: 600,
                  height: containerHeights,
                  decoration: BoxDecoration(
                    color: Color(0xFFead0d1),
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(20),
                      bottomLeft: Radius.circular(20)
                    ),
                    boxShadow: [
                      BoxShadow(color: customColors['primary']!, spreadRadius: 2)
                    ]
                    ), 
                    child: Padding(
                      padding: const EdgeInsets.all(10.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          SizedBox(height: 10,),
                          Text("Send us a message!",
                           style: TextStyle(
                             fontWeight: FontWeight.bold,
                             fontSize: 16
                           ) ,),
                          Text("Send us a message and we'll respond within 24 hours.",
                          style: TextStyle(
                            color: Colors.grey[600],
                            fontSize: 12
                          ),),
                          SizedBox(height: 30,),
                          Row(
                            children: [
                              SizedBox(width: 500 /1.9,
                              child: buildForm('name'),),
                              SizedBox(width: 40,),
                              SizedBox(width: 500/1.9,
                              child: buildForm('email'),)
                            ],
                          ),
                          SizedBox(height: 20,),
                          Row(
                            children: [
                              SizedBox(width: 500 /1.9,
                              child: buildForm('phone'),),
                              SizedBox(width: 40,),
                            ],
                          ),
                          SizedBox(height: 20,),
                          buildForm('message'),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              SizedBox(
                                height: 55,
                                width: 150,
                                child: PrimaryButton(
                                  title: "Submit",
                                  hoverElevation: 20,
                                  onTap: () => {/* TODO: Implement Submit*/},
                                ),
                              ),
                            ],
                          )

                        ],
                      ),
                    ),),
                    Container(
                  width: 300,
                  height: this.containerHeights,
                  decoration: BoxDecoration(
                    color: Color(0xFFead0d1),
                    borderRadius: BorderRadius.only(
                      topRight: Radius.circular(20),
                      bottomRight: Radius.circular(20)
                    ),
                    boxShadow: [
                      BoxShadow(color: customColors['primary']!, spreadRadius: 2)
                    ]
                    ), 
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        contactDetailsFragment(
                          Icons.location_pin, "SpyGlass Office Address"),
                          SizedBox(height: 20,),
                          contactDetailsFragment(Icons.email, "contact-us@spyglass.com"),
                          SizedBox(height: 20,),
                          contactDetailsFragment(Icons.phone, "+1-000-000-000")
                      ],
                      
                    ),
                    )
              ],)
            ],
          ),
        ),
    ),
      ),
    )
    );
  }

}