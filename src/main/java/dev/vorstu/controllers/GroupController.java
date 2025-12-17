package dev.vorstu.controllers;

import dev.vorstu.dto.GroupDTO;
import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.dto.TeacherDTO;
import dev.vorstu.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*api/base/")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping(value = "groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public GroupDTO addGroup(@RequestBody GroupDTO groupDTO){
        return groupService.addGroup(groupDTO);
    }

    // todo при загрузке студента будет два запроса
    @GetMapping(value = "groups/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO getGroupById(@PathVariable("id") Long id){
        return groupService.getGroupById(id);
    }

    @GetMapping(value = "groups/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupNameDTO> getAllGroupNames() {
        return groupService.getAllGroupNames();
    }

    @DeleteMapping(value = "groups/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Long deleteGroup(@PathVariable("id") Long id) {
        return groupService.deleteGroup(id);
    }

    @PatchMapping(value = "groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public GroupDTO updateGroup(GroupDTO groupDTO){
        return groupService.updateGroup(groupDTO);
    }

    // todo /{id}/{teacherId} - норм или не норм?
    @PostMapping(value = "group/{id}/teacher", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public GroupDTO addTeacherToGroup(@PathVariable("id") Long groupId, @RequestBody TeacherDTO teacherDTO){
        return groupService.addTeacherToGroup(groupId, teacherDTO);
    }

}
