{
  <#if data.blockType == "Normal">
"inputsInline": true,
  "previousStatement": null,
  "nextStatement": <#if data.hasNextStatement>null<#else>false</#if>,
  <#elseif data.blockType == "Output">
"output": "${data.outputType}",
  </#if>
  "colour": "${data.hexColor}",
  "mcreator": {
    "toolbox_id": "${data.category}",
    "dependencies": [
        <#list data.dependencies as dependency>
        {
            "name": "${dependency.getName()}",
            "type": "${dependency.getRawType()}"
        }<#if dependency?has_next>,</#if>
        </#list>
      ]
  }
}