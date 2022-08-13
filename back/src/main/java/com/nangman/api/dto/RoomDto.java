package com.nangman.api.dto;
import com.nangman.db.entity.Bus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel("Room model")
public class RoomDto {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest{
        @ApiModelProperty(name="생성할 룸의 버스 정보", example="77바8236")
        private Bus bus;
    }
}