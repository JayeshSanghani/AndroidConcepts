package com.android.androidconcepts.topics

import java.lang.reflect.Method

/**
This object TELIPUtils created here can be used to access private variables from the classes and
also to assign new data to the private variables in the classes.
-> object : It is the instance of the class in which the private variable exists.
-> fieldName : The variable name as declared in class.
-> value : It is the Value to be assigned to the private variable in the class
/ values of the parameter of the function
-> dataType: It is data types of the parameters of the function
-------------------------------------------------------------------------------------------------
``````````````````````````setVariableData``````````````````````````
This function allows us to set the data to private variable by object, fieldName and value as parameters.

``````````````````````````getVariableData``````````````````````````
This function allows us to get the data from private variable by object, fieldName as parameters.

`````````````````````````getFunctionAccess`````````````````````````
This function allows us to access the private function by object, fieldName and dataType.

`````````````````````````accessFunction`````````````````````````
This function allows us to invoke the private function by object, fieldName and values.
 **/


object TELIPUtils {
    fun setVariableData(`object`: Any, fieldName: String, value: Any?) {
        val field = `object`.javaClass.getDeclaredField(fieldName)
        field.isAccessible = true
        field[`object`] = value
    }
    //Usage: TELIPUtils.setVariableData(endOfContentCardFragment!!,"isSeries", true)

    fun getVariableData(`object`: Any, fieldName: String): Any? {
        val field = `object`.javaClass.getDeclaredField(fieldName)
        field.isAccessible = true
        return field.get(`object`)
    }
    //Usage:TELIPUtils.getVariableData(fragment!!,"title")

    fun getFunctionAccess(`object`: Any, fieldName: String, vararg dataType: Class<*>): Method {
        val method =
            `object`.javaClass.getDeclaredMethod(fieldName, *dataType)
        method.isAccessible = true
        return method
    }
    //Usage: TELIPUtils.getFunctionAccess(fragment!!,"initTranslations").accessFunction(fragment!!)
    //       TELIPUtils.getFunctionAccess(fragment!!,"fetchEpisodeTitle", Int::class.java)
    //                    .accessFunction(fragment!!, 0)

    fun Method.accessFunction(`object`: Any, vararg value: Any?): Any? {
        return this.invoke(`object`, *value)
    }
    //Usage:  TELIPUtils.getFunctionAccess(fragment!!,"initTranslations").accessFunction(fragment!!)
    //        TELIPUtils.getFunctionAccess(fragment!!,"fetchEpisodeTitle", Int::class.java)
    //                 .accessFunction(fragment!!, 0)
}
