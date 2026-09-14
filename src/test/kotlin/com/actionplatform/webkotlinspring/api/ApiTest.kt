package com.actionplatform.webkotlinspring.api

import com.actionplatform.webkotlinspring.Version
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ApiTest(
    @Autowired private val mvc: MockMvc,
) {
    @Test
    fun ping() {
        mvc
            .perform(get("/ping"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").value("ok"))
            .andExpect(jsonPath("$.version").value(Version.VERSION))
    }

    @Test
    fun `creates lists and gets items`() {
        mvc
            .perform(post("/api/v1/items").contentType(MediaType.APPLICATION_JSON).content("""{"name": "pen"}"""))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value("pen"))
        mvc.perform(get("/api/v1/items")).andExpect(status().isOk).andExpect(jsonPath("$[0].name").value("pen"))
        mvc.perform(get("/api/v1/items/1")).andExpect(status().isOk).andExpect(jsonPath("$.id").value(1))
    }

    @Test
    fun `errors have one shape`() {
        mvc
            .perform(post("/api/v1/items").contentType(MediaType.APPLICATION_JSON).content("""{"name": "  "}"""))
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.detail.code").value("invalid"))
            .andExpect(jsonPath("$.detail.field").value("name"))
        mvc
            .perform(get("/api/v1/items/999"))
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.detail.code").value("not_found"))
    }
}
